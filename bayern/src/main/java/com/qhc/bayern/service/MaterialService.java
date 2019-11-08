package com.qhc.bayern.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhc.bayern.controller.entity.Bom;
import com.qhc.bayern.controller.entity.BomBodyParam;
import com.qhc.bayern.controller.entity.BomHeadParam;
import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.Material;
import com.qhc.bayern.controller.entity.Parameter;
import com.qhc.bayern.util.DateUtil;
import com.qhc.bayern.util.HttpUtil;
import com.qhc.bayern.util.StrToDouble;


@Service
public class MaterialService {
	
	@Autowired
	private FryeService<List<Material>> fryeService;
	
	@Value("${sap.material.addr}")
	String materialUrlStr;
	
	@Value("${sap.bomExplosion.addr}")
	String bomExplosionUrlStr;
	
	private final static String LAST_UPDATED_DATE = "material/lastUpdateDate";
	private final static String PUT_MATERIAL = "material";
	
	//
	public final static String BOM_CONFIGURATION_DEFAULT = "default";
	public final static String BOM_CONFIGURATION_CONFIGURATED = "configurated";
	public final static String MATERIAL_BOM_CODE ="bom_code";
	public final static String WERKS ="0841";
	public final static String STLAN ="1";
	
	
	public String getLastUpdate() {
		return fryeService.getLastUpdatedDate(LAST_UPDATED_DATE);
	}
	
	public List<Material> getNewestMaterialsFromSap() {
		List<Material> mlist = new ArrayList<Material>();
		try {
			String pingGuLei="1000,3101,3102,3104,3105,3109,3212,3233,3235,3237,9101,9102,9103";
			String dateParameter = this.getLastUpdate();
			
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LAEDA");
			parameter1.setValue(dateParameter.substring(0,8));
			Parameter parameter2 = new Parameter();
			parameter2.setKey("UZEIT");
			parameter2.setValue(dateParameter.substring(8,14));
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			parList.add(parameter2);
			String paymentplanParam = JSONObject.toJSONString(parList); 
			//发送请求获取数据
			String bb = HttpUtil.postbody(materialUrlStr, paymentplanParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object Data = parseObject.get("data");
			JSONArray DataArray = JSONArray.parseArray(Data.toString());
			for (int i = 0; i < DataArray.size();i++) { 
				JSONObject obj = (JSONObject)DataArray.get(i);
				if("".equals(obj.getString("meins"))) {
					System.out.println(obj.getString("matnr")+":计量单位不能为空");
				}else if(pingGuLei.indexOf(obj.getString("bklas")) == -1) {
					System.out.println(obj.getString("matnr")+":评估类不正确");
				}else {
					//
					Boolean configurable = ("X".equals(obj.getString("kzkfg")))?true:false;
					Boolean purchased = ("E".equals(obj.getString("beskz")))?true:false;
					String clazzCode = ("".equals(obj.getString("class")))?"unconfigurable":obj.getString("class");
					String groupCode = ("".equals(obj.getString("bklas")))?"1000":obj.getString("bklas");
					
					Material material = new Material();
//					material.setUnitCode("SZ");
					
					material.setCode(obj.getString("matnr"));
					material.setDescription(obj.getString("maktx"));
					material.setConfigurable(configurable);
					material.setPurchased(purchased);
					material.setStandardPrice(StrToDouble.test(obj.getString("verpr")));
					//
					material.setOptTime(DateUtil.convert2Date(obj.getString("laeda")+obj.getString("laetm"), "yyyyMMddHHmmss"));
					material.setUnitCode(obj.getString("meins"));
					material.setGroupCode(groupCode);
					material.setClazzCode(clazzCode);
					material.setMaterialSize(StrToDouble.test(obj.getString("volum")));
					mlist.add(material);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mlist;
	}
	//循环调用物料接口，同步物料数据
	public void saveNewestMaterialsFromSap() {
		for (int i = 0; i < 1000; i++) {
			List<Material> matList = this.getNewestMaterialsFromSap();
			if(matList.size() > 0) {
				this.uploadMaterials(matList);
			}else {
				System.out.println("物料数据抽取完毕");
				break;
			}
		}
	}
	
	public void uploadMaterials(List<Material> materials) {
		fryeService.putJason(PUT_MATERIAL, materials);
	}
	
	
	//BOM
	public Map<String, List> getBomExplosion(Map<String, String> mapParam) {
		Map<String, List> map = new HashMap<String, List>();
		
		try {
			List<Bom> bomList1 = new ArrayList<Bom>();
			//请求参数
			BomHeadParam bomHeadParam = new BomHeadParam();
			bomHeadParam.setMatnr(mapParam.get(MATERIAL_BOM_CODE));
			bomHeadParam.setWerks(WERKS);
			bomHeadParam.setStlan(STLAN);
			//去掉map中的物料号，只留特征，循环插入list
			mapParam.remove(MATERIAL_BOM_CODE);
			List list = new ArrayList<>();
			for (Map.Entry<String, String> entity : mapParam.entrySet()) {
				BomBodyParam atnam = new BomBodyParam();
				atnam.setAtnam(entity.getKey());
				atnam.setAtwrt(entity.getValue());
				list.add(atnam);
			}
			bomHeadParam.setCharac(list);
			//BOM接口的请求参数
			String bomParam = JSONObject.toJSONString(bomHeadParam);
				
			//发送请求获取数据
			String bb = HttpUtil.postbody(bomExplosionUrlStr, bomParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			Object data_def = parseObject.get("data_def");
			JSONArray dataArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < dataArray.size();i++) { 
				JSONObject obj = (JSONObject)dataArray.get(i);
				//
				Boolean configurable = (!"".equals(obj.getString("kzkfg")))?true:false;
				Boolean marked = (!"".equals(obj.getString("mark")))?true:false;
				//
				Bom bom = new Bom();
				bom.setCode(obj.getString("matnr_stpo"));
				bom.setParent(obj.getString("matnr"));
				bom.setConfigurable(configurable);
				bom.setPrice(StrToDouble.test(obj.getString("stprs")));
				bom.setQuantity(StrToDouble.test(obj.getString("menge")));
				bom.setMarked(marked);
				bomList1.add(bom);
			}
			
			List<Bom> bomList2 = new ArrayList<Bom>();
			JSONArray dataDefArray = JSONArray.parseArray(data_def.toString());
			for (int i = 0; i < dataDefArray.size();i++) { 
				JSONObject obj = (JSONObject)dataDefArray.get(i);
				//
				Boolean configurable = (!"".equals(obj.getString("kzkfg")))?true:false;
				Boolean marked = (!"".equals(obj.getString("mark")))?true:false;
				//
				Bom bom = new Bom();
				bom.setCode(obj.getString("matnr_stpo"));
				bom.setParent(obj.getString("matnr"));
				bom.setConfigurable(configurable);
				bom.setPrice(StrToDouble.test(obj.getString("stprs")));
				bom.setQuantity(StrToDouble.test(obj.getString("menge")));
				bom.setMarked(marked);
				bomList2.add(bom);
			}
			//
			map.put(BOM_CONFIGURATION_CONFIGURATED, bomList1);
			map.put(BOM_CONFIGURATION_DEFAULT, bomList2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
