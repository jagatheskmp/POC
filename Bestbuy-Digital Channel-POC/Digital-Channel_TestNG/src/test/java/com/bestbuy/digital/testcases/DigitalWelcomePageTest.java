package com.bestbuy.digital.testcases;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bestbuy.digital.Screens.BaseScreen;
import com.bestbuy.digital.selenium.util.ScreenException;
import com.bestbuy.digital.uiconstants.DigitalUIData;
import com.bestbuy.digital.uiconstants.DigitalUIIdentifiers;
import com.bestbuy.digital.uiconstants.Env_Config;
import com.bestbuy.digital.uiconstants.UserData;




/**
 * This class is to Test the major functionalities of the Synonyms Tab
 */

public class DigitalWelcomePageTest {

	private Log log = LogFactory.getLog("Digital");

	private static String selectedBrowser;	
	private static DigitalUIIdentifiers digitalUI;
	private static Env_Config digitalEnv;
	private static DigitalUIData digitalUiData;
	private static UserData digUserData;
	private String methodName;
	private static BaseScreen baseScreen;

	@BeforeTest
	public static void setUp() throws Exception {
		digitalEnv = new Env_Config();		
		digitalUI = new DigitalUIIdentifiers();
		digitalUiData = new DigitalUIData();
		digUserData = new UserData();
		try {
			launchingBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is called in the setUp method which instantiates the browser
	 * session
	 */

	public static void launchingBrowser() throws Exception {
		String applicationURL = digitalEnv.getProtocol() + "://" + digitalEnv.getHost() + ":" + digitalEnv.getPort() + "/";
		selectedBrowser = digitalEnv.getBrowser();
		try {
			baseScreen = new BaseScreen(selectedBrowser, applicationURL,digitalEnv.getContext(), digitalEnv, digitalUI,digitalUiData,digUserData);
		} catch (ScreenException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testRegistration()
	throws InterruptedException, IOException, Exception {
		try {
			log.info("Executing Registration Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.registration(methodName);
		} catch (Exception t) {
			log.info("Exception in Registration Scenario"+t.getMessage());
		}
	}

	@Test
	public void testLogin()
	throws InterruptedException, IOException, Exception {
		try {
			log.info("Executing login Scenario");
			methodName = Thread.currentThread().getStackTrace()[1]
			                                                    .getMethodName();
			baseScreen.login(methodName);
		} catch (Exception t) {
			log.info("Exception in login Scenario"+t.getMessage());
		}
	}

	@AfterTest
	public static void tearDown() {
		baseScreen.closeBrowser();
	}

}
