package com.bestbuy.digital.Screens;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bestbuy.digital.selenium.util.Constants;
import com.bestbuy.digital.selenium.util.GetCurrentDir;
import com.bestbuy.digital.selenium.util.MagicNumbers;
import com.bestbuy.digital.selenium.util.ScreenException;
import com.bestbuy.digital.uiconstants.DigitalUIData;
import com.bestbuy.digital.uiconstants.DigitalUIIdentifiers;
import com.bestbuy.digital.uiconstants.Env_Config;
import com.bestbuy.digital.uiconstants.UserData;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.google.common.base.Function;

/**
 * This class contains the pre-defined or user defined methods that are called
 * in the Test methods
 */

public class BaseScreen {

	private static WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	protected static WebElement element;
	private static Env_Config digitalEnv;
	private static DigitalUIIdentifiers digitalUI;
	private static DigitalUIData digitalUiData;
	private static UserData digUserData;
	private boolean value;

	private static HtmlUnitDriver htmlDriver;

	public BaseScreen() {

	}

	/**
	 * This method instantiates browser's driver
	 * 
	 * @param selectedBrowser
	 *            current selected Browser where the test cases will execute
	 * 
	 * @param applicationURL
	 *            Application URL
	 * 
	 * @param applicationContext
	 *            Application Context
	 * 
	 * @param digitalEnv
	 *            Instance to the environment configurations class
	 * 

	 * 
	 */
	public BaseScreen(String selectedBrowser, String applicationURL,
			String applicatinContext, Env_Config digitalEnv,			
			DigitalUIIdentifiers digitalUI,DigitalUIData digitalUiData,UserData digUserData) throws ScreenException {

		BaseScreen.digitalEnv = digitalEnv;
		BaseScreen.digitalUI = digitalUI;
		BaseScreen.digitalUiData = digitalUiData;
		BaseScreen.digUserData = digUserData;

		instantiateBrowser(selectedBrowser, applicationURL, applicatinContext);

	}

	/**
	 * This method instantiates browser's driver
	 * 
	 * @param selectedBrowser
	 *            current selected Browser where the test cases will execute
	 * 
	 * @param applicationURL
	 *            Application URL
	 * 
	 * @param applicationContext
	 *            Application Context
	 */

	public void instantiateBrowser(String selectedBrowser,
			String applicationURL, String applicationContext)
	throws ScreenException {

		/*if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			try	{ 
				chromeService = new ChromeDriverService.Builder().usingDriverExecutable(new File(getChromeLocation())).usingAnyFreePort().build();

				log.info("LAUNCHING GOOGLECHROME"); 
				driver = new ChromeDriver(chromeService); 
				windowResize();
				driver.navigate().to(applicationURL + applicationContext);

			} catch (Exception e) {
				log.info("EXCEPTION IN LAUNCHING GOOGLECHROME" + e.getMessage()); }

		}*/

		 if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info("LAUNCHING INTERNET EXPLORE");
			driver = new InternetExplorerDriver();
			windowResize();
			driver.navigate().to(applicationURL + applicationContext);

		} else if (selectedBrowser.equalsIgnoreCase(Constants.IPHONE_HYBRID)) {
			log.info("LAUNCHING IPHONE SIMULATOR/DEVICE");
			try {
				driver = new IPhoneDriver();
			} catch (Exception e) {
				log.info("EXCEPTION IN LAUNCHING SIMULATOR/DEVICE"
						+ e.getMessage());
			}
			driver.navigate().to(applicationURL + applicationContext);

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info("LAUNCHING FIREFOX");
			if (isUnix()) {
				System.setProperty("webdriver.firefox.bin","/usr/local/bin/firefox701_for_fta");
			}
			driver = new FirefoxDriver();
			windowResize();
			driver.navigate().to(applicationURL + applicationContext);

		} else if (selectedBrowser.equalsIgnoreCase(Constants.ANDROID_HYBRID)) {
			log.info("LAUNCHING ANDROID SIMULATOR/DEVICE");
			driver = new AndroidDriver();
			driver.get(applicationURL + applicationContext);
			log.info("----------------------Waiting for page to load----------------");
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_OPERA)) {
			log.info("LAUNCHING OPERA");
			Robot robot;
			try {
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_SPACE);
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_X);
			} catch (AWTException e) {

				log.info("EXCEPTION IN LAUNCHING OPERA" + e.getMessage());
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.HTML_UNIT_DRIVER)) {
			log.info("LAUNCHING HTML_UNIT");
			if (isUnix()) {
				System.setProperty("webdriver.firefox.bin",	"/usr/local/bin/firefox701_for_fta");
			}
			htmlDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10);
			htmlDriver.setJavascriptEnabled(true);

			java.util.logging.Logger.getLogger("com.gargoylesoftware")
			.setLevel(Level.OFF);
			htmlDriver.get(applicationURL + applicationContext);

		} else {
			throw new ScreenException(
			"Only FireFox,IExplore,Chrome,opera,android and iphone browsers works");
		}

	}

	/**
	 * This method sets different resolution for the window
	 */

	public static void windowResize() {

		String resolution = digitalEnv.getResolution();
		if (resolution != null) {
			String[] tokens = resolution.split("x");
			String resolutionX = tokens[0];
			String resolutionY = tokens[1];
			int x = Integer.parseInt(resolutionX);
			int y = Integer.parseInt(resolutionY);
			Dimension screenResolution = new Dimension(x, y);
			driver.manage().window().setSize(screenResolution);
		} else {
			driver.manage().window().maximize();
		}
	}

	/**
	 * This method is called in the Test classes for termination of the Browser
	 * session
	 */

	public void closeBrowser() {
		log.info("BROWSER CLOSING");
		if (driver != null) {
			driver.quit();
		} else {
			log.info("DRIVER NOT EXIST TO QUIT ");
		}

	}

	public static boolean isUnix() {
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS
				.indexOf("aix") > 0);

	}

	/**
	 * This method is to get the location of the Chrome Driver
	 */

	/*public String getChromeLocation() {
		log.info("getChromeLocation:CHROME TARGET LOCATION FOUND"); String
		directory = System.getProperty("user.dir"); String targetDirectory = getChromeFile(); 
		String location = directory + targetDirectory; 
		return location; 
	}*/


	/**
	 * This method is to get the Chrome Driver based on the OS
	 */

	/*public String getChromeFile() {
		if(System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)) {
			log.info("WINDOWS MACHINE FOUND");
			return Constants.CHROME_DIRECTORY + "/chromedriver.exe"; 
		} 
		else if(System.getProperty("os.name").startsWith(Constants.LINUX_OS)) {
			log.info("LINUX MACHINE FOUND");
			return Constants.CHROME_DIRECTORY + "/chromedriver";
		} 
		else if(System.getProperty("os.name").startsWith(Constants.MAC_OS)) {
			log.info("MAC MACHINE FOUND");
			return Constants.CHROME_DIRECTORY + "/chromedriver";
		} 
		else { 
			throw new NullPointerException("PLATFORM NOT FOUND");

		}

	}*/


	/**
	 * This method is to get the xpath of the Web element
	 * 
	 * @param xpath
	 *            It is the identifier of an UI object
	 */

	public WebElement getXpathWebElement(String xpath) {
		log.info("Entering getXpathWebElement");
		try {
			if (digitalEnv.getBrowser().equalsIgnoreCase("htmlunit")) {
				element = htmlDriver.findElement(By.xpath(xpath));
			} else {
				element = driver.findElement(By.xpath(xpath));
			}
		} catch (Exception e) {
			log.info("Exception in getXpathWebElement" + e.getMessage());
		}
		return element;
	}

	/**
	 * This method is to get the Id of the Web element
	 * 
	 * @param id
	 *            It is the identifier of an UI object
	 */

	public void getIdWebElement(String id) throws ScreenException {
		log.info("Entering getIdWebElement");
		try {
			if (digitalEnv.getBrowser().equalsIgnoreCase("htmlunit")) {
				element = htmlDriver.findElement(By.id(id));
			} else {
				element = driver.findElement(By.id(id));
			}
		} catch (Exception e) {
			log.info("Exception in getIdWebElement" + e.getMessage());
		}
	}

	/**
	 * This method is to get the css of the Web element
	 * 
	 * @param selector
	 *            It is the identifier of an UI object
	 */

	public void getcssWebElement(String selector) throws ScreenException {
		log.info("Entering getIdWebElement");
		try {
			if (digitalEnv.getBrowser().equalsIgnoreCase("htmlunit")) {
				element = htmlDriver.findElement(By.cssSelector(selector));
			} else {
				element = driver.findElement(By.cssSelector(selector));
			}
		} catch (Exception e) {
			log.info("Exception in getIdWebElement" + e.getMessage());
		}
	}

	/**
	 * This method waits for presence of specific xpath or Id of the Web element
	 * and takes screen shot if it is not available
	 * 
	 * @param locator
	 *            It is the Identifier of the UI object
	 * @param methodName
	 *            It stores the method Name from which the call is triggered
	 */

	public void waitForElementPresent(String locator, String methodName) {
		try {
			if (digitalEnv.getBrowser().equalsIgnoreCase("htmlunit")) {
				log.info("ENTERING IN HTML WAIT");
				Thread.sleep(MagicNumbers.FIVE_THOUSAND_SECONDS);
			} else {
				By by = null;
				log.info("Entering waitForElementPresent");

				if (locator.startsWith("//")) {
					log.info("Entering Xpath checker");
					by = By.xpath(locator);
				} else {
					log.info("Entering Non-Xpath checker");
					by = By.id(locator);
				}
				WebDriverWait wait = new WebDriverWait(driver,MagicNumbers.FOURTY_SECONDS);
				wait.until(presenceOfElementLocated(by));
			}
		} catch (Exception e) {
			log.info("presenceOfElementLocated" + e.getMessage());
			File scrFile = ((TakesScreenshot) driver)
			.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile,
						new File(GetCurrentDir.getCurrentDirectory() + "\\"
								+ methodName + ".png"));
			} catch (Exception e1) {
				log.info("presenceOfElementLocated" + e1.getMessage());
			}
			Assert.assertNull(scrFile);

		}
	}

	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("Entering presenceOfElementLocated");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					Thread.sleep(MagicNumbers.ONE_THOUSAND_SECONDS);
					log.info("Waiting for Element to present");
				} catch (InterruptedException e) {
					log.info("Exception in presenceOfElementLocated"
							+ e.getMessage());
				}
				return driver.findElement(locator);
			}
		};
	}



	/**
	 * This method is to select a particular Text from the Application UI
	 * 
	 * @param element
	 *            Instance to the WebElement class
	 * @param textValue
	 *            Text to be selected in the UI
	 */

	public void selectText(WebElement element, String textValue)
	throws ScreenException {
		log.info("Entering get Select Text Webelement");

		Select selObj = new Select(element);
		selObj.selectByVisibleText(textValue);

	}

	/**
	 * This method is to click on a particular Web element
	 */

	public void click() throws ScreenException {
		log.info("Click operation start");
		try {
			element.click();
		} catch (Exception e) {
			log.info("Exception in click" + e.getMessage());
		}
		log.info("Click operation end");
	}

	/**
	 * This method is to clear a particular Text from the Application UI
	 */

	public void clear() throws ScreenException {
		log.info("Clear operation start");
		try {
			element.clear();
		} catch (Exception e) {
			log.info("Exception in clear" + e.getMessage());
		}
		log.info("Clear operation end");

	}

	/**
	 * This method is to type a particular Text its an alternate of the type
	 * method
	 * 
	 * @param text
	 *            The text to be passed as value for the Text field in the UI
	 */

	public void sendKeys(String text) throws ScreenException {
		log.info("EnterText operation start");
		try {
			element.sendKeys(text);
		} catch (Exception e) {
			log.info("Exception in sendKeys" + e.getMessage());
		}
		log.info("EnterText operation end");
	}

	public void enterKeys(String value, String value1) throws ScreenException {
		log.info("EnterText operation start");
		try {
			element = driver.findElement(By.xpath(value));
			element.clear();
			element.sendKeys(value1);
			Thread.sleep(MagicNumbers.ONE_THOUSAND_SECONDS);
			element.sendKeys(Keys.RETURN);
			Thread.sleep(MagicNumbers.THREE_THOUSAND_SECONDS);

		} catch (Exception e) {
			log.info("Exception in sendKeys" + e.getMessage());
		}
		log.info("EnterText operation end");
	}

	/**
	 * This method is to click on the submit button
	 */

	public void submit() throws ScreenException {
		log.info("Submit operation start");
		try {
			element.submit();
		} catch (Exception e) {
			log.info("Exception in submit" + e.getMessage());
		}
		log.info("Submit operation end");
	}

	/**
	 * This method is to verify the presence of particular Text
	 * 
	 * @param text
	 *            The text to be found in the UI
	 * @throws InterruptedException
	 */

	public boolean isTextPresent(String text,String methodName) throws InterruptedException ,AssertionFailedError{
		try {
			Thread.sleep(MagicNumbers.FIVE_THOUSAND_SECONDS);
			if (digitalEnv.getBrowser().equalsIgnoreCase("htmlunit")
					&& text != null) {
				value = htmlDriver.findElement(By.tagName("body")).getText()
				.contains(text);
			} else if (text != null) {
				value = driver.findElement(By.tagName("body")).getText().contains(text);
			} 
			Assert.assertTrue(value);
		} catch (AssertionFailedError e) {
			takesScreenshot(methodName);
			log.info("Text not present" + e.getMessage());
		}
		return value;

	}


	/**
	 * Method to perform Drag and drop functionality
	 * 
	 * @param dragelement
	 *            ElementID of the element to be dragged
	 */
	public void dragAndDrop(String dragelement) {

		WebElement dragElement = driver.findElement(By.xpath(dragelement));
		new Actions(driver)
		.dragAndDropBy(dragElement, MagicNumbers.X_POSITION,
				MagicNumbers.Y_POSITION).build().perform();

	}

	public void takesScreenshot(String methodName) {

		log.info("presenceOfElementLocated");
		File scrFile = ((TakesScreenshot) driver)
		.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File(GetCurrentDir.getCurrentDirectory() + "\\"
							+ methodName + ".png"));
		} catch (Exception e1) {
			log.info("presenceOfElementLocated" + e1.getMessage());
		}

	}

	public void invalidLogin(String methodName) throws AssertionFailedError, IOException, Exception{
		try {
			log.info("Entering invalidLogin");
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSignButton(), methodName);
			getXpathWebElement(digitalUI.getSignButton());
			click();
			waitForElementPresent(digitalUI.getLoginEmail(), methodName);
			getXpathWebElement(digitalUI.getLoginEmail());
			sendKeys(digUserData.getLoginEmail());
			waitForElementPresent(digitalUI.getLoginPassword(), methodName);
			getXpathWebElement(digitalUI.getLoginPassword());
			sendKeys(digitalUiData.getInvalidPassword());
			waitForElementPresent(digitalUI.getLoginButton(), methodName);
			getXpathWebElement(digitalUI.getLoginButton());
			click();
			Assert.assertTrue(isTextPresent(digitalUiData.getInvalidLoginError(), methodName));
		} catch (ScreenException e) {
			log.info("ScreenException in invalidLogin "+e.getMessage());
		}
	}

	public void registration(String methodName) throws IOException, Exception{
		try {
			log.info("Entering registration");
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getMyAccount(), methodName);
			getXpathWebElement(digitalUI.getMyAccount());
			click();
			waitForElementPresent(digitalUI.getCreateAccount(), methodName);
			getXpathWebElement(digitalUI.getCreateAccount());
			click();
			waitForElementPresent(digitalUI.getFirstName(), methodName);
			getXpathWebElement(digitalUI.getFirstName());
			sendKeys(digitalUiData.getRegFirstName());
			waitForElementPresent(digitalUI.getLastName(), methodName);
			getXpathWebElement(digitalUI.getLastName());
			sendKeys(digitalUiData.getRegLastName());
			waitForElementPresent(digitalUI.getEmailId(), methodName);
			getXpathWebElement(digitalUI.getEmailId());
			sendKeys(digitalUiData.getRegEmailId());
			waitForElementPresent(digitalUI.getReTypeEmailId(), methodName);
			getXpathWebElement(digitalUI.getReTypeEmailId());
			sendKeys(digitalUiData.getRegReEmailId());
			waitForElementPresent(digitalUI.getPassword(), methodName);
			getXpathWebElement(digitalUI.getPassword());
			sendKeys(digitalUiData.getRegPassword());
			waitForElementPresent(digitalUI.getReTypepassword(), methodName);
			getXpathWebElement(digitalUI.getReTypepassword());
			sendKeys(digitalUiData.getRegRePassword());
			waitForElementPresent(digitalUI.getZipCode(), methodName);
			getXpathWebElement(digitalUI.getZipCode());
			sendKeys(digitalUiData.getRegZipCode());
			waitForElementPresent(digitalUI.getContinueButton(), methodName);
			getXpathWebElement(digitalUI.getContinueButton());
			click();
			waitForElementPresent(digitalUI.getSignoutButton(), methodName);
			getXpathWebElement(digitalUI.getSignoutButton());
			click();
			waitForElementPresent(digitalUI.getSignButton(), methodName);
		} catch (ScreenException e) {
			log.info("ScreenException in registration "+e.getMessage());
		}
	}

	public void login(String methodName) throws IOException, Exception{
		try {
			log.info("Entering login");
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getBestBuyHomebutton(), methodName);
			getXpathWebElement(digitalUI.getBestBuyHomebutton());
			click();
			waitForElementPresent(digitalUI.getSignButton(), methodName);
			getXpathWebElement(digitalUI.getSignButton());
			click();
			waitForElementPresent(digitalUI.getLoginEmail(), methodName);
			getXpathWebElement(digitalUI.getLoginEmail());
			sendKeys(digUserData.getLoginEmail());
			waitForElementPresent(digitalUI.getLoginPassword(), methodName);
			getXpathWebElement(digitalUI.getLoginPassword());
			sendKeys(digUserData.getLoginPassword());
			waitForElementPresent(digitalUI.getLoginButton(), methodName);
			getXpathWebElement(digitalUI.getLoginButton());
			click();
			waitForElementPresent(digitalUI.getSignoutButton(), methodName);
		} catch (ScreenException e) {
			log.info("ScreenException in login "+e.getMessage());
		}
	}

	public void selectProduct(String methodName) throws AssertionFailedError, IOException, Exception{
		try {
			log.info("Entering selectProduct");
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getBestBuyHomebutton(), methodName);
			getXpathWebElement(digitalUI.getBestBuyHomebutton());
			click();
			waitForElementPresent(digitalUI.getWeeklyDealsButton(), methodName);
			getXpathWebElement(digitalUI.getWeeklyDealsButton());
			click();
			waitForElementPresent(digitalUI.getComputerTabletsLink(), methodName);
			getXpathWebElement(digitalUI.getComputerTabletsLink());
			click();
			waitForElementPresent(digitalUI.getIpadTabletsLink(), methodName);
			getXpathWebElement(digitalUI.getIpadTabletsLink());
			click();
			waitForElementPresent(digitalUI.getSamsungGalaxyLink(), methodName);
			getXpathWebElement(digitalUI.getSamsungGalaxyLink());
			click();
			waitForElementPresent(digitalUI.getAddToCardButton(), methodName);
			getXpathWebElement(digitalUI.getAddToCardButton());
			click();
			waitForElementPresent(digitalUI.getCheckoutButton(), methodName);
			getXpathWebElement(digitalUI.getCheckoutButton());
			click();
			waitForElementPresent(digitalUI.getLoginEmail(), methodName);
			getXpathWebElement(digitalUI.getLoginEmail());
			clear();
			sendKeys(digUserData.getLoginEmail());
			waitForElementPresent(digitalUI.getCheckOutSigninPassword(), methodName);
			getXpathWebElement(digitalUI.getCheckOutSigninPassword());
			clear();
			sendKeys(digUserData.getLoginPassword());
			waitForElementPresent(digitalUI.getLoginButton(), methodName);
			getXpathWebElement(digitalUI.getLoginButton());
			click();
			waitForElementPresent(digitalUI.getAddress1(), methodName);
			getXpathWebElement(digitalUI.getAddress1());
			sendKeys(digitalUiData.getAddress1());
			waitForElementPresent(digitalUI.getAddress2(), methodName);
			getXpathWebElement(digitalUI.getAddress2());
			sendKeys(digitalUiData.getAddress2());
			waitForElementPresent(digitalUI.getCity(), methodName);
			getXpathWebElement(digitalUI.getCity());
			sendKeys(digitalUiData.getCity());
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getState(), methodName);
			getXpathWebElement(digitalUI.getState());
			selectText(element,digitalUiData.getState());
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getPhoneNo(), methodName);
			getXpathWebElement(digitalUI.getPhoneNo());
			sendKeys(digitalUiData.getPhoneNo());
			waitForElementPresent(digitalUI.getCheckoutContinue(), methodName);
			getXpathWebElement(digitalUI.getCheckoutContinue());
			click();
			Assert.assertTrue(isTextPresent("success",methodName));
		} catch (ScreenException e) {

			log.info("ScreenException in selectProduct "+e.getMessage());
		}
	}


	public void searchProduct(String methodName) throws AssertionFailedError, IOException, Exception{
		try {
			log.info("Entering searchProduct");
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getBestBuyHomebutton(), methodName);
			getXpathWebElement(digitalUI.getBestBuyHomebutton());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSearchBox(), methodName);
			getXpathWebElement(digitalUI.getSearchBox());
			click();
			sendKeys(digitalUiData.getSearchproduct());
			Thread.sleep(MagicNumbers.THREE_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSearchButton(), methodName);
			getXpathWebElement(digitalUI.getSearchButton());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSortByDropdown(), methodName);
			getXpathWebElement(digitalUI.getSortByDropdown());
			sendKeys(digitalUiData.getSortByValue());
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSelectItem(), methodName);
			getXpathWebElement(digitalUI.getSelectItem());
			click();
			waitForElementPresent(digitalUI.getAddToCardButton(), methodName);
		} catch (ScreenException e) {
			log.info("ScreenException in searchProduct "+e.getMessage());
		}
	}
	public void removeProduct(String methodName) throws AssertionFailedError, IOException, Exception{
		try {
			log.info("Entering removeProduct");
			Thread.sleep(MagicNumbers.THREE_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getBestBuyHomebutton(), methodName);
			getXpathWebElement(digitalUI.getBestBuyHomebutton());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getUpdateCartButton(), methodName);
			getXpathWebElement(digitalUI.getUpdateCartButton());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getRemoveProduct1(), methodName);
			getXpathWebElement(digitalUI.getRemoveProduct1());
			click();
			Assert.assertTrue(isTextPresent(digitalUiData.getRemoveProductVerify(),methodName));
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
		} catch (ScreenException e) {
			log.info("ScreenException in removeProduct "+e.getMessage());
		}
	}

	public void compareAndRemoveProduct(String methodName) throws AssertionFailedError, IOException, Exception{
		try {
			log.info("Entering compareAndRemoveProduct");
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getBestBuyHomebutton(), methodName);
			getXpathWebElement(digitalUI.getBestBuyHomebutton());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getCompareProduct(), methodName);
			getXpathWebElement(digitalUI.getCompareProduct());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSortByDropdown(), methodName);
			getXpathWebElement(digitalUI.getSortByDropdown());
			selectText(element, digitalUiData.getCompareSortbyValue());
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getFirstProduct(), methodName);
			getXpathWebElement(digitalUI.getFirstProduct());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSecondProduct(), methodName);
			getXpathWebElement(digitalUI.getSecondProduct());
			click();
			Assert.assertTrue(isTextPresent(digitalUiData.getCompareProductVerify(),methodName));
			waitForElementPresent(digitalUI.getRemoveCompareProduct(), methodName);
			getXpathWebElement(digitalUI.getRemoveCompareProduct());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getRemoveCompareProduct(), methodName);
			getXpathWebElement(digitalUI.getRemoveCompareProduct());
			click();
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getSortByDropdown(), methodName);
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
		} catch (ScreenException e) {
			log.info("ScreenException in compareAndRemoveProduct "+e.getMessage());
		}
	}

	public void logOut(String methodName) throws AssertionFailedError, IOException, Exception{
		try {
			log.info("Entering logOut");
			Thread.sleep(MagicNumbers.TWO_THOUSAND_SECONDS);
			waitForElementPresent(digitalUI.getBestBuyHomebutton(), methodName);
			getXpathWebElement(digitalUI.getBestBuyHomebutton());
			click();
			waitForElementPresent(digitalUI.getSignoutButton(), methodName);
			getXpathWebElement(digitalUI.getSignoutButton());
			click();
			waitForElementPresent(digitalUI.getSignButton(), methodName);
			
		} catch (ScreenException e) {
			log.info("ScreenException in logOut "+e.getMessage());
		}
	}



}
