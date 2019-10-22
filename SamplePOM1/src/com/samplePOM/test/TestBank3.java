package com.samplePOM.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.samplePOM.page.LoginGuru99Bank;
import com.samplePOM.page.LogoutPage;
import com.samplePOM.page.ManagerHomePage;
import com.samplePOM.page.MiniStatementPage;
import com.samplePOM.reportListeners.ExtentReportManager;
import com.samplePOM.reportListeners.ExtentTestManager;
import com.samplePOM.utilities.Constants;

public class TestBank3 extends BaseTest{

	LoginGuru99Bank loginPage = null;
	ManagerHomePage managerHomePage = null;
	MiniStatementPage miniStatementPage = null;
	LogoutPage logoutPage = null;

	@BeforeTest()
	public void initConstr(){
		loginPage = new LoginGuru99Bank(driver);
		managerHomePage = new ManagerHomePage(driver);
		miniStatementPage = new MiniStatementPage(driver);
		logoutPage = new LogoutPage(driver);
		logger.info("cons created successfully");
	}

	@Test(priority=0)
	public void launchURL(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		driver.get(Constants.webURL);
		ExtentTestManager.getExtentTest().log(LogStatus.PASS, "TC Passed");
		logger.info("URL is launched successfully");
	}

	@Test(priority=1)
	public void loginApp(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		loginPage.enterUserName(Constants.userID);
		loginPage.enterPassword(Constants.password);
		loginPage.clickLoginBtn();
		commonUtil.waitForElementContainsText(ManagerHomePage.by_managerIDTxt, 10, Constants.managerHomePageString);
		logger.info("logged-in to bank successfully");
		ExtentTestManager.getExtentTest().log(LogStatus.PASS, "TC Passed");
	}

	@Test(priority=2)
	public void validateMiniStatement(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		managerHomePage.clickMiniStatementLink();
		logger.info("mini statement link created successfully");
		commonUtil.waitForElementContainsText(MiniStatementPage.by_txt_MiniStatementForm, 10, Constants.textMiniStatementForm);
		miniStatementPage.enterAccountNo("44463");
		miniStatementPage.clickSubmitBTN();
		commonUtil.waitForElementContainsText(MiniStatementPage.by_txt_LastFiveTransDetails, 10, Constants.textLastFiveTransactionDetails);
		logger.info("account no entered and details fetched successfully");
		miniStatementPage.clickContinueBTN();
		ExtentTestManager.getExtentTest().log(LogStatus.PASS, "TC Passed");
	}

	/******* for testing purpose only  **********/
	/********* test code begins *******************/

	@Test(priority=3)
	public void failTest(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		Assert.assertTrue(false);
		//logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
		ExtentTestManager.getExtentTest().log(LogStatus.PASS, "Test Case (failTest) Status is passed");
	}

	@Test(priority=4)
	public void skipTest(){
		ExtentTestManager.setExtentTest(ExtentReportManager.getExtentReport().startTest(new Object(){}.getClass().getEnclosingMethod().getName()));
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	/********* testing code ends ***************/

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

	@AfterTest()
	public void logoutBank(){
		commonUtil.waitForElementContainsText(ManagerHomePage.by_managerIDTxt, 10, Constants.managerHomePageString);
		managerHomePage.clickLogoutLink();
		logoutPage.handlePopup();
		logger.info("logged-out of bank successfully");
		/*ExtentReportManager.getExtentReport().endTest(ExtentTestManager.getExtentTest());
		ExtentReportManager.getExtentReport().flush();*/
	}

}