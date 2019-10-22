package com.samplePOM.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.samplePOM.reportListeners.ExtentReportManager;
import com.samplePOM.reportListeners.ExtentTestManager;
import com.samplePOM.reportListeners.TestListener;
import com.samplePOM.utilities.CommonUtilities;
import com.samplePOM.utilities.GridConfigSetUp;

public class BaseTest {
	
	private DesiredCapabilities capability = null;
	public WebDriver driver = null;
	public CommonUtilities commonUtil = null;
	public static Logger logger = LogManager.getLogger(Driver.class.getName());

	@BeforeTest(alwaysRun=true)
	@Parameters({"platformName", "browser", "remoteURL", "deviceType", "platformVersion", 
		"udid", "deviceName"})
	public void initSetUp(ITestContext testContext, String platformName, @Optional String browser, String remoteURL, 
			String deviceType, String platformVersion, @Optional String udid, String deviceName){
		ThreadContext.push("threadID", Thread.currentThread().getId());
		initDriver(platformName, browser, remoteURL, deviceType, platformVersion, udid, deviceName);
		initReportTest(testContext);
		//launchURL();
		logger.info("************* Test case "+testContext.getName()+" start excuting *****************");
	}
	
	public void initDriver(String platformName, @Optional String browser, String remoteURL, 
			String deviceType, String platformVersion, @Optional String udid, String deviceName){
		logger.info("Currently executing parameters for this run: DeviceType="+deviceType+", DeviceName="+deviceName+", BrowserName="+browser);
		if(deviceType.equalsIgnoreCase(GridConfigSetUp.DeviceType.Desktop.toString())){
			if(browser.equalsIgnoreCase(GridConfigSetUp.Browser.Chrome.toString())){
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("--disable-web-security");
				chromeOptions.addArguments("--no-proxy-server");
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("--disable-extenstions");
				chromeOptions.addArguments("disable-infobars");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("password_manager_enabled", false); 
				chromeOptions.setExperimentalOption("prefs", prefs);
				capability = DesiredCapabilities.chrome();
				capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			}else if(browser.equalsIgnoreCase(GridConfigSetUp.Browser.Firefox.toString())){
				FirefoxOptions options = new FirefoxOptions();
				options.setBinary("C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
				capability = DesiredCapabilities.firefox();
				capability.setCapability("moz:firefoxOptions", options);
			}
			try {
				driver = new RemoteWebDriver(new URL(remoteURL), capability);
				logger.info("desktop driver instance created successfully");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				logger.info("Error! during creating new driver instance");
			}
		}else if(deviceType.equalsIgnoreCase(GridConfigSetUp.DeviceType.Mobile.toString())){
			capability = new DesiredCapabilities();
			if(platformName.equalsIgnoreCase(GridConfigSetUp.PlatformName.Android.toString())){
				capability.setCapability("platformName", platformName);
				capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
				capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
				capability.setCapability(MobileCapabilityType.UDID, udid);
				if(browser.equalsIgnoreCase(GridConfigSetUp.Browser.Chrome.toString())){
					capability.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
					capability.setCapability("chromedriverExecutable", "D:\\soft\\jars\\webdrivers_driver\\chrome_driver_2.40\\chromedriver_win32\\chromedriver.exe");
				}else if(browser.equalsIgnoreCase(GridConfigSetUp.Browser.NA.toString())){
					capability.setCapability("appPackage", "io.selendroid.testapp");
					capability.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");
				}
				try {
					driver = new AndroidDriver<AndroidElement>(new URL(remoteURL), capability);
					logger.info("mobile Android driver instance created successfully");
				} catch (MalformedURLException e) {
					e.printStackTrace();
					logger.info("Error! during creating new Android driver instance");
				}
			}else if(platformName.equalsIgnoreCase(GridConfigSetUp.PlatformName.iOS.toString())){
				
			}
		}
		commonUtil = new CommonUtilities(driver);
	}
	
	public void initReportTest(ITestContext testContext){
		XmlTest xmlTest = testContext.getCurrentXmlTest();
		Map<String, String> testParameter = xmlTest.getAllParameters();
		String reportName = xmlTest.getParameter("reportName");
		if(TestListener.reportFiles.keySet().contains(reportName.toLowerCase())){
			ExtentReports _extent;
			_extent = new ExtentReports(TestListener.reportFiles.get(reportName.toLowerCase()), true);
			_extent.loadConfig(new File(System.getProperty("user.dir")+"\\runtime\\extent_config.xml"));
			//_extent.addSystemInfo("OS", testParameter.get("platformName"));
			_extent.addSystemInfo("Device Type", testParameter.get("deviceType"));
			if(testParameter.keySet().contains("platformVersion")){
				//_extent.addSystemInfo("Version", testParameter.get("platformVersion"));
				_extent.addSystemInfo("OS", testParameter.get("platformName")+testParameter.get("platformVersion"));
			}else{
				//_extent.addSystemInfo("Version", "NA");
				_extent.addSystemInfo("OS", testParameter.get("platformName"));
			}
			if(testParameter.keySet().contains("deviceName")){
				_extent.addSystemInfo("Device Name", testParameter.get("deviceName"));
			}else{
				_extent.addSystemInfo("Device Name", "NA");
			}
			ExtentReportManager.setExtentReport(_extent);
			logger.info("Report object generated successfully for "+reportName+" ,along with the execution parameter\'s information");
		}else{
			System.out.println(reportName+" file is not created in the Listener");
			logger.info("Please check the .xml file for execution, "+reportName+" is not present under the list of expected extent results "+TestListener.reportFiles.keySet().toString());
		}
	}
	
	/*//@Test(priority=-1)
	public void launchURL(){
		//ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		driver.get(Constants.webURL);
		//ExtentTestManager.getExtentTest().log(LogStatus.PASS, "TC Passed");
		logger.info("URL is launched successfully");
	}*/
	
	@AfterTest()
	public void killBrowser(ITestContext testContext){
		ExtentReportManager.getExtentReport().endTest(ExtentTestManager.getExtentTest());
		ExtentReportManager.getExtentReport().flush();
		driver.close();
		//System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		//driver.quit();//commented as this throws error in firefox;for chrome it works fine
		logger.info("browser successfully killed");
		logger.info("driver instance successfully killed");
		logger.info("************* Test case "+testContext.getName() +" terminates excuting *****************");
		ThreadContext.remove("threadID");
	}
	
	@AfterSuite()
	public void endAllReports(){
		//close
		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
        //Once this method is called, calling any Extent method will throw an error.
        //close() - To close all the operation
        //extent.close();
		//System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		//ExtentReportManager.getExtentReport().close();
		ExtentReportManager.extentReportManager.remove();
		logger.info("************* Test suite terminates excuting *****************");
		//logger.info("************** test terminates *************");
	}
}