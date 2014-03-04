package com.bestbuy.digital.uiconstants;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DigitalUIData {

	private Log log = LogFactory.getLog("Digital");

	private String invalidPassword = "invalidPassword"; 
	private String invalidLoginError = "invalidLoginError";
	private String regFirstName = "regFirstName";
	private String regLastName = "regLastName";
	private String regEmailId = "regEmailId";
	private String regReEmailId = "regReEmailId";
	private String regPassword = "regPassword";
	private String regRePassword= "regRePassword";
	private String regZipCode= "regZipCode";
	private String state= "state";
	private String address1 = "address1";
	private String address2 = "address2";
	private String city = "city";
	private String phoneNo = "phoneNo";
	private String removeProductVerify = "removeProductVerify";
	private String searchproduct = "searchproduct";
	private String sortByValue = "sortByValue";
	private String compareProductVerify = "compareProductVerify";
	private String compareSortbyValue = "compareSortbyValue";
	private String compareProductafterRemove = "compareProductafterRemove";
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

	
	

	public String getCompareProductafterRemove() {
		return compareProductafterRemove;
	}

	public void setCompareProductafterRemove(String compareProductafterRemove) {
		this.compareProductafterRemove = compareProductafterRemove;
	}

	public String getCompareProductVerify() {
		return compareProductVerify;
	}

	public void setCompareProductVerify(String compareProductVerify) {
		this.compareProductVerify = compareProductVerify;
	}

	public String getCompareSortbyValue() {
		return compareSortbyValue;
	}

	public void setCompareSortbyValue(String compareSortbyValue) {
		this.compareSortbyValue = compareSortbyValue;
	}

	public String getSearchproduct() {
		return searchproduct;
	}

	public void setSearchproduct(String searchproduct) {
		this.searchproduct = searchproduct;
	}

	public String getSortByValue() {
		return sortByValue;
	}

	public void setSortByValue(String sortByValue) {
		this.sortByValue = sortByValue;
	}

	public String getInvalidLoginError() {
		return invalidLoginError;
	}

	public void setInvalidLoginError(String invalidLoginError) {
		this.invalidLoginError = invalidLoginError;
	}

	public String getInvalidPassword() {
		return invalidPassword;
	}

	public void setInvalidPassword(String invalidPassword) {
		this.invalidPassword = invalidPassword;
	}

	public String getRemoveProductVerify() {
		return removeProductVerify;
	}

	public void setRemoveProductVerify(String removeProductVerify) {
		this.removeProductVerify = removeProductVerify;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
