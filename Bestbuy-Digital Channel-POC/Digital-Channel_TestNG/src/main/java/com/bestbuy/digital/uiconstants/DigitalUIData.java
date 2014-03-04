package com.bestbuy.digital.uiconstants;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DigitalUIData {

	private Log log = LogFactory.getLog("Digital");

	private String regFirstName = "regFirstName";
	private String regLastName = "regLastName";
	private String regEmailId = "regEmailId";
	private String regReEmailId = "regReEmailId";
	private String regPassword = "regPassword";
	private String regRePassword= "regRePassword";
	private String regZipCode= "regZipCode";
	private String state= "state";

	public DigitalUIData() {
		try {
			ReadXMLFile readXml = new ReadXMLFile();
			readXml.loadDigitalUIData();
			Field[] arrayOfField = this.getClass().getDeclaredFields();
			for (Field field : arrayOfField) {
				field.setAccessible(true);
				Object localObject = field.get(this);
				if (localObject instanceof String) {
					field.set(this, readXml.getValue((String) localObject));
				}
			}
		} catch (Exception localException) {
			log.info("Exception in DigitalUIData"
					+ localException.getMessage());
		}
	}

	public String getRegFirstName() {
		return regFirstName;
	}

	public void setRegFirstName(String regFirstName) {
		this.regFirstName = regFirstName;
	}

	public String getRegLastName() {
		return regLastName;
	}

	public void setRegLastName(String regLastName) {
		this.regLastName = regLastName;
	}

	public String getRegEmailId() {
		return regEmailId;
	}

	public void setRegEmailId(String regEmailId) {
		this.regEmailId = regEmailId;
	}

	public String getRegReEmailId() {
		return regReEmailId;
	}

	public void setRegReEmailId(String regReEmailId) {
		this.regReEmailId = regReEmailId;
	}

	public String getRegPassword() {
		return regPassword;
	}

	public void setRegPassword(String regPassword) {
		this.regPassword = regPassword;
	}

	public String getRegRePassword() {
		return regRePassword;
	}

	public void setRegRePassword(String regRePassword) {
		this.regRePassword = regRePassword;
	}

	public String getRegZipCode() {
		return regZipCode;
	}

	public void setRegZipCode(String regZipCode) {
		this.regZipCode = regZipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
}
