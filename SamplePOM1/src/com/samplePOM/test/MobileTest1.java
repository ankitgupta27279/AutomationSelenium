package com.samplePOM.test;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.samplePOM.page.MobileWikiHomePage;
import com.samplePOM.reportListeners.ExtentReportManager;
import com.samplePOM.reportListeners.ExtentTestManager;

public class MobileTest1 extends BaseTest{
	
	MobileWikiHomePage mobileWikiHomePage = null;
	
	@BeforeTest
	public void initConstr(){
		mobileWikiHomePage = new MobileWikiHomePage(driver);
		logger.info("mobile cons created successfully");
	}

	@Test(priority=0)
	public void accessWikionWeb(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		driver.get("https://www.wikipedia.org/");
		commonUtil.waitForElementContainsText(mobileWikiHomePage.wikiTextOnHomePage, 10, "Wikipedia");
		ExtentTestManager.getExtentTest().log(LogStatus.PASS, "TC Passed");
		logger.info("URL is launched successfully");
	}
	
	@Test(priority=1)
	public void searchTextInTB(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		mobileWikiHomePage.enterTextInWikiSearchTB("Earth");
		ExtentTestManager.getExtentTest().log(LogStatus.PASS, "TC Passed");
		logger.info("entered text in TB is launched successfully");
	}
	
	@Test(priority=2)
	public void clickSearchButton(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		mobileWikiHomePage.clickSearchBTN();
		ExtentTestManager.getExtentTest().log(LogStatus.PASS, "TC Passed");
		logger.info("search button successfully clicked");
	}
	
	@AfterMethod()
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			//ExtentTestManager.getExtentTest().log(LogStatus.FAIL, "Testcase failed is "+result.getName());
			ExtentTestManager.getExtentTest().log(LogStatus.FAIL, "Testcase failed is "+result.getThrowable());
			ExtentTestManager.getExtentTest().log(LogStatus.FAIL, ExtentTestManager.getExtentTest().addScreenCapture(commonUtil.getScreenshot(result.getName())));
		}else if(result.getStatus() == ITestResult.SKIP){
			ExtentTestManager.getExtentTest().log(LogStatus.SKIP, "Testcases skipped is "+result.getName());
		}
	}
}
