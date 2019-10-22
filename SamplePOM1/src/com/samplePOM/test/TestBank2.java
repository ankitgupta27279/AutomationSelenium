package com.samplePOM.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.samplePOM.page.BalanceEnquiryPage;
import com.samplePOM.page.LoginGuru99Bank;
import com.samplePOM.page.LogoutPage;
import com.samplePOM.page.ManagerHomePage;
import com.samplePOM.utilities.Constants;

public class TestBank2 extends BaseTest{

	LoginGuru99Bank loginPage = null;
	ManagerHomePage managerHomePage = null;
	BalanceEnquiryPage balanceEnquiryPage = null;
	LogoutPage logoutPage = null;
	
	@BeforeTest()
	public void initConstr(){
		loginPage = new LoginGuru99Bank(driver);
		managerHomePage = new ManagerHomePage(driver);
		balanceEnquiryPage = new BalanceEnquiryPage(driver);
		logoutPage = new LogoutPage(driver);
	}
	
	@Test(priority=1)
	public void loginApp(){
		loginPage.enterUserName(Constants.userID);
		loginPage.enterPassword(Constants.password);
		loginPage.clickLoginBtn();
		commonUtil.waitForElementContainsText(ManagerHomePage.by_managerIDTxt, 10, Constants.managerHomePageString);
	}
	
	@Test(priority=2)
	public void enquireBalance(){
		managerHomePage.clickBalanceEnquiryLink();
		commonUtil.waitForElementContainsText(BalanceEnquiryPage.by_txt_BalanceEnquiryForm, 10, Constants.textBalanceEnquiryForm);
		balanceEnquiryPage.enterAccountNo("43156");
		balanceEnquiryPage.clickSubmitBTN();
		commonUtil.waitForElementContainsText(BalanceEnquiryPage.by_txt_BalanceDetails, 10, Constants.textBalanceDetailsAccount);
		balanceEnquiryPage.clickContinueBTN();
	}
	
	@Test(priority=3)
	public void logoutBank(){
		commonUtil.waitForElementContainsText(ManagerHomePage.by_managerIDTxt, 10, Constants.managerHomePageString);
		managerHomePage.clickLogoutLink();
		logoutPage.handlePopup();
	}
}