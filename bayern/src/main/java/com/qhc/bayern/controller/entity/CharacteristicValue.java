/**
 * 
 */
package com.qhc.bayern.controller.entity;

/**
 * @author wang@dxc.com
 *
 */
public class CharacteristicValue{
	
	private String code;// 物料value  atwrt
	
	private String name;//物料值描述     atwtb
	
	private String characteristicCode; //物料编号   atnam
	
	private String CharacteristicName;// 物料描述   atbez
	
	private String clazzCode;//类     class

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharacteristicCode() {
		return characteristicCode;
	}

	public void setCharacteristicCode(String characteristicCode) {
		this.characteristicCode = characteristicCode;
	}

	public String getCharacteristicName() {
		return CharacteristicName;
	}

	public void setCharacteristicName(String characteristicName) {
		CharacteristicName = characteristicName;
	}

	public String getClazzCode() {
		return clazzCode;
	}

	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}
	
	
}
