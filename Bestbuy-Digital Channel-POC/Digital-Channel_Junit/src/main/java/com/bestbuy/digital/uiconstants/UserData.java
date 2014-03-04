package com.bestbuy.digital.uiconstants;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserData {
	private Log log = LogFactory.getLog("Digital");

	private String loginEmail = "loginEmail";
	private String loginPassword = "loginPassword";


	public UserData() {
		try {
			ReadXMLFile readXml = new ReadXMLFile();
			readXml.loadDigitalUserData();
			Field[] arrayOfField = this.getClass().getDeclaredFields();
			for (Field field : arrayOfField) {
				field.setAccessible(true);
				Object localObject = field.get(this);
				if (localObject instanceof String) {
					field.set(this, readXml.getValue((String) localObject));
				}
			}
		} catch (Exception localException) {
			log.info("Exception in UserData"
					+ localException.getMessage());
		}
	}


	public String getLoginEmail() {
		return loginEmail;
	}


	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}


	public String getLoginPassword() {
		return loginPassword;
	}


	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


}

