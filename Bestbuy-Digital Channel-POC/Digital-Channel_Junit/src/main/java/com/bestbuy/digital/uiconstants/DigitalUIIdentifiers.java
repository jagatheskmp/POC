package com.bestbuy.digital.uiconstants;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class where variables of MerchandisingUIIdentifiers.xml are defined
 */

public class DigitalUIIdentifiers {

	private Log log = LogFactory.getLog("Digital");

	private String bestBuyHomebutton = "bestBuyHomebutton";
	private String myAccount = "myAccount";
	private String createAccount = "createAccount";
	private String firstName = "firstName";
	private String lastName = "lastName";
	private String emailId= "emailId";
	private String reTypeEmailId= "reTypeEmailId";
	private String password= "password";
	private String reTypepassword= "reTypepassword";
	private String zipCode= "zipCode";
	private String continueButton = "continueButton";
	private String signButton = "signButton";
	private String loginEmail = "loginEmail";
	private String loginPassword = "loginPassword";
	private String loginButton = "loginButton";
	private String weeklyDealsButton = "weeklyDealsButton";
	private String computerTabletsLink = "computerTabletsLink";
	private String ipadTabletsLink = "ipadTabletsLink";
	private String samsungGalaxyLink = "samsungGalaxyLink";
	private String addToCardButton = "addToCardButton";
	private String signoutButton = "signoutButton";
	private String checkoutButton = "checkoutButton";
	private String checkOutSigninPassword = "checkOutSigninPassword";
	private String address1 = "address1";
	private String address2 = "address2";
	private String city = "city";
	private String state = "state";
	private String phoneNo = "phoneNo";
	private String checkoutContinue = "checkoutContinue";
	private String updateCartButton = "updateCartButton";
	private String removeProduct1 = "removeProduct1";
	private String removeProduct2 = "removeProduct2";
	private String searchBox = "searchBox";
	private String searchButton = "searchButton";
	private String sortByDropdown = "sortByDropdown";
	private String selectItem = "selectItem";
	private String compareProduct = "compareProduct";
	private String firstProduct = "firstProduct";
	private String secondProduct = "secondProduct";
	private String removeCompareProduct = "removeCompareProduct";
	
	public DigitalUIIdentifiers() {
		try {
			ReadXMLFile readXml = new ReadXMLFile();
			readXml.loadDigitalUIConstants();
			Field[] arrayOfField = this.getClass().getDeclaredFields();
			for (Field field : arrayOfField) {
				field.setAccessible(true);
				Object localObject = field.get(this);
				if (localObject instanceof String) {
					field.set(this, readXml.getValue((String) localObject));
				}
			}
		} catch (Exception localException) {
			log.info("Exception in DigitalUIIdentifiers"
					+ localException.getMessage());
		}
	}

	
	
	public String getRemoveProduct1() {
		return removeProduct1;
	}

	public void setRemoveProduct1(String removeProduct1) {
		this.removeProduct1 = removeProduct1;
	}

	public String getRemoveProduct2() {
		return removeProduct2;
	}

	public void setRemoveProduct2(String removeProduct2) {
		this.removeProduct2 = removeProduct2;
	}

	public String getRemoveCompareProduct() {
		return removeCompareProduct;
	}

	public void setRemoveCompareProduct(String removeCompareProduct) {
		this.removeCompareProduct = removeCompareProduct;
	}

	public String getCompareProduct() {
		return compareProduct;
	}

	public void setCompareProduct(String compareProduct) {
		this.compareProduct = compareProduct;
	}

	public String getFirstProduct() {
		return firstProduct;
	}

	public void setFirstProduct(String firstProduct) {
		this.firstProduct = firstProduct;
	}

	public String getSecondProduct() {
		return secondProduct;
	}

	public void setSecondProduct(String secondProduct) {
		this.secondProduct = secondProduct;
	}

	public String getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(String searchBox) {
		this.searchBox = searchBox;
	}

	public String getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(String searchButton) {
		this.searchButton = searchButton;
	}

	public String getSortByDropdown() {
		return sortByDropdown;
	}

	public void setSortByDropdown(String sortByDropdown) {
		this.sortByDropdown = sortByDropdown;
	}

	public String getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(String selectItem) {
		this.selectItem = selectItem;
	}

	public String getUpdateCartButton() {
		return updateCartButton;
	}

	public void setUpdateCartButton(String updateCartButton) {
		this.updateCartButton = updateCartButton;
	}

	

	public String getCheckOutSigninPassword() {
		return checkOutSigninPassword;
	}

	public void setCheckOutSigninPassword(String checkOutSigninPassword) {
		this.checkOutSigninPassword = checkOutSigninPassword;
	}
	public String getBestBuyHomebutton() {
		return bestBuyHomebutton;
	}


	public void setBestBuyHomebutton(String bestBuyHomebutton) {
		this.bestBuyHomebutton = bestBuyHomebutton;
	}


	public String getMyAccount() {
		return myAccount;
	}


	public void setMyAccount(String myAccount) {
		this.myAccount = myAccount;
	}


	public String getCreateAccount() {
		return createAccount;
	}


	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getReTypeEmailId() {
		return reTypeEmailId;
	}


	public void setReTypeEmailId(String reTypeEmailId) {
		this.reTypeEmailId = reTypeEmailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getReTypepassword() {
		return reTypepassword;
	}


	public void setReTypepassword(String reTypepassword) {
		this.reTypepassword = reTypepassword;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getContinueButton() {
		return continueButton;
	}


	public void setContinueButton(String continueButton) {
		this.continueButton = continueButton;
	}


	public String getSignButton() {
		return signButton;
	}


	public void setSignButton(String signButton) {
		this.signButton = signButton;
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


	public String getLoginButton() {
		return loginButton;
	}


	public void setLoginButton(String loginButton) {
		this.loginButton = loginButton;
	}


	public String getSignoutButton() {
		return signoutButton;
	}


	public void setSignoutButton(String signoutButton) {
		this.signoutButton = signoutButton;
	}


	public String getWeeklyDealsButton() {
		return weeklyDealsButton;
	}


	public void setWeeklyDealsButton(String weeklyDealsButton) {
		this.weeklyDealsButton = weeklyDealsButton;
	}


	public String getComputerTabletsLink() {
		return computerTabletsLink;
	}


	public void setComputerTabletsLink(String computerTabletsLink) {
		this.computerTabletsLink = computerTabletsLink;
	}


	public String getIpadTabletsLink() {
		return ipadTabletsLink;
	}


	public void setIpadTabletsLink(String ipadTabletsLink) {
		this.ipadTabletsLink = ipadTabletsLink;
	}


	public String getSamsungGalaxyLink() {
		return samsungGalaxyLink;
	}


	public void setSamsungGalaxyLink(String samsungGalaxyLink) {
		this.samsungGalaxyLink = samsungGalaxyLink;
	}


	public String getAddToCardButton() {
		return addToCardButton;
	}


	public void setAddToCardButton(String addToCardButton) {
		this.addToCardButton = addToCardButton;
	}




	public String getCheckoutButton() {
		return checkoutButton;
	}


	public void setCheckoutButton(String checkoutButton) {
		this.checkoutButton = checkoutButton;
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


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getCheckoutContinue() {
		return checkoutContinue;
	}


	public void setCheckoutContinue(String checkoutContinue) {
		this.checkoutContinue = checkoutContinue;
	}



}
