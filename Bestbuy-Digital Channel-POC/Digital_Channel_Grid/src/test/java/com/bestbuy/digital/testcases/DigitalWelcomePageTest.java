package com.bestbuy.digital.testcases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bestbuy.digital.Screens.BaseScreen;
import com.bestbuy.digital.uiconstants.DigitalUIData;
import com.bestbuy.digital.uiconstants.DigitalUIIdentifiers;
import com.bestbuy.digital.uiconstants.Env_Config;
import com.bestbuy.digital.uiconstants.UserData;


public class DigitalWelcomePageTest {

	private static DigitalUIIdentifiers digitalUI;
	private static Env_Config digitalEnv;
	private static DigitalUIData digitalUiData;
	private static UserData digUserData;
	private static String methodName;
	private static BaseScreen baseScreen;

	@Parameters(value = {"browser","platform"})
	@BeforeTest
	public void setUp(String browser, String platform) throws Exception {
		String selectedBrowser  =  browser;
		String selectedPlatform  =  platform;
		digitalEnv = new Env_Config();		
		digitalUI = new DigitalUIIdentifiers();
		digitalUiData = new DigitalUIData();
		digUserData = new UserData();
		try {
			String applicationURL = digitalEnv.getProtocol() + "://" + digitalEnv.getHost() + ":" + digitalEnv.getPort() + "/";
			baseScreen = new BaseScreen(selectedBrowser, selectedPlatform, applicationURL,digitalEnv.getContext(), digitalEnv, digitalUI,digitalUiData,digUserData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidLogin()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testInvalidLogin Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.invalidLogin(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testInvalidLogin Scenario"+t.getMessage());
		}
	}

	@Test
	public void testRegistration()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testRegistration Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.registration(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testRegistration Scenario"+t.getMessage());
		}
	}

	@Test
	public void testLogin()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testLogin Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.login(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testLogin Scenario"+t.getMessage());
		}
	}

	@Test
	public void testSelectProduct()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testSelectProduct Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.selectProduct(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testSelectProduct Scenario"+t.getMessage());
		}
	}

	@Test
	public void testRemoveProduct()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testRemoveProduct Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.removeProduct(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testRemoveProduct Scenario"+t.getMessage());
		}
	}

	@Test
	public void testSearchProduct()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testSearchProduct Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.searchProduct(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testSearchProduct Scenario"+t.getMessage());
		}
	}

	@Test
	public void testCompareAndRemoveProduct()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testCompareAndRemoveProduct Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.compareAndRemoveProduct(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testCompareAndRemoveProduct Scenario"+t.getMessage());
		}
	}

	@Test
	public void testLogout()
	throws InterruptedException, IOException, Exception {
		try {
			System.out.println("Executing testLogout Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.logOut(methodName);
		} catch (Exception t) {
			baseScreen.takesScreenshot(methodName);
			System.out.println("Exception in testLogout Scenario"+t.getMessage());
		}
	}

	@AfterTest
	public void tearDown() {
		baseScreen.closeBrowser();
	}

}
