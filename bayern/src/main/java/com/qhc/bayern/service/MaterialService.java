package com.qhc.bayern.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
	
	private final static String LAST_UPDATED_DATE = "material/lastUpdateDate";
	private final static String PUT_MATERIAL = "material";
	
	public String getLastUpdate() {
		return fryeService.getLastUpdatedDate(LAST_UPDATED_DATE);
	}
	
	public List<Material> getNewestMaterialsFromSap() {
		List<Material> mlist = new ArrayList<Material>();
		try {
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
				//
				Boolean configurable = ("X".equals(obj.getString("kzkfg")))?true:false;
				Boolean purchased = ("E".equals(obj.getString("beskz")))?true:false;
				
				Material material = new Material();
				material.setUnitCode("SZ");
				material.setGroupCode("FA01");
				material.setClazzCode("unconfigurable");
				
				material.setCode(obj.getString("matnr"));
				material.setDescription(obj.getString("maktx"));
				material.setConfigurable(configurable);
				material.setPurchased(purchased);
				material.setStandardPrice(StrToDouble.test(obj.getString("verpr")));
				//
				material.setOptTime(DateUtil.convert2Date(obj.getString("laeda")+obj.getString("laetm"), "yyyyMMddHHmmss"));
//				material.setUnitCode(obj.getString("meins"));
//				material.setGroupCode(obj.getString("matkl"));
//				material.setClazzCode(obj.getString("class"));
				material.setMaterialSize(StrToDouble.test(obj.getString("volum")));
				mlist.add(material);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mlist;
	}

	public void saveNewestMaterialsFromSap() {
		for (int i = 0; i < 100; i++) {
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

}
