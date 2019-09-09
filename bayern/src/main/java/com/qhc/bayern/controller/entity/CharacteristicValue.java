/**
 * 
 */
package com.qhc.bayern.controller.entity;

/**
 * @author wang@dxc.com
 *
 */
public class CharacteristicValue extends AbsConObject {
	private String characteristicCode; 
	private String CharacteristicName;
	private String clazzCode;
	
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
