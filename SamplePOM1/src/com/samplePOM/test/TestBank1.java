package com.samplePOM.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.samplePOM.page.AccountCreateMsgPage;
import com.samplePOM.page.AddAccountPage;
import com.samplePOM.page.AddCustomerPage;
import com.samplePOM.page.CustomerRegMsgPage;
import com.samplePOM.page.DepositPage;
import com.samplePOM.page.LoginGuru99Bank;
import com.samplePOM.page.LogoutPage;
import com.samplePOM.page.ManagerHomePage;
import com.samplePOM.utilities.Constants;

public class TestBank1 extends BaseTest{
	
	LoginGuru99Bank loginPage = null;
	ManagerHomePage managerHomePage = null;
	AddCustomerPage addCustomerPage = null;
	CustomerRegMsgPage custRegMsgPage = null;
	AddAccountPage addAccountPage = null;
	AccountCreateMsgPage actCreateMsgPage = null;
	DepositPage depositPage = null;
	LogoutPage logoutPage = null;
	String custID = null;
	String actID = null;
	Alert alert = null;
	
	@BeforeTest
	public void initPageConstr(){
		loginPage = new LoginGuru99Bank(driver);
		managerHomePage = new ManagerHomePage(driver);
		addCustomerPage = new AddCustomerPage(driver);
		custRegMsgPage = new CustomerRegMsgPage(driver);
		addAccountPage = new AddAccountPage(driver);
		actCreateMsgPage = new AccountCreateMsgPage(driver);
		depositPage = new DepositPage(driver);
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
	public void addNewCustomer(){
		managerHomePage.clickNewCustomerLink();
		addCustomerPage.enterCustName(Constants.custName1);
		addCustomerPage.clickGenderMale();
		addCustomerPage.enterDOB(Constants.dob1);
		addCustomerPage.enterAddress(Constants.address1);
		addCustomerPage.enterCity(Constants.city1);
		addCustomerPage.enterState(Constants.state1);
		addCustomerPage.enterPinNo(Constants.pin1);
		addCustomerPage.enterMobileNo(Constants.mobileNo1);
		addCustomerPage.enterEmailID(Constants.email1);
		addCustomerPage.enterPassword(Constants.pass1);
		try{
			addCustomerPage.clickSubmitBTN();
			commonUtil.waitForElementContainsText(CustomerRegMsgPage.by_custIDText, 10, Constants.textCustomerID);
			custID = commonUtil.getTextFromElement(CustomerRegMsgPage.custIDEle);
			custRegMsgPage.clickContinueBTN();
		}catch(UnhandledAlertException unhandledAlertExp){
			try{
				alert = driver.switchTo().alert();
				System.out.println("alert detected-"+alert.getText());
				alert.accept();
			}catch(NoAlertPresentException noAlertExp){
				noAlertExp.printStackTrace();
			}
		}
	}
	
	@Test(priority=3)
	public void addNewAccount(){
		managerHomePage.clickNewAccountLink();
		addAccountPage.enterCustID(custID);
		addAccountPage.selectAccountType(Constants.accountTypeSaving);
		addAccountPage.enterInitialDeposit(Constants.intialDeposit);
		addAccountPage.clickSumitBTN();
		commonUtil.waitForElementContainsText(AccountCreateMsgPage.by_actIDText, 10, Constants.textAccountID);
		actID = commonUtil.getTextFromElement(AccountCreateMsgPage.accountIDEle);
		actCreateMsgPage.clickContinueBTN();
	}
	
	@Test(priority=4)
	public void depositMoney(){
		managerHomePage.clickDepositLink();
		depositPage.enterAccountNo(actID);
		depositPage.enterAmount("2500");
		depositPage.enterDescription("amount added");
		depositPage.clickSubmitBTN();
		commonUtil.waitForElementContainsText(DepositPage.by_TransactionDetails, 10, Constants.textTransactionDetails);
		depositPage.clickContinueBTN();
	}
	
	//@Test(priority=5)
	public void logoutBank(){
		commonUtil.waitForElementContainsText(ManagerHomePage.by_managerIDTxt, 10, Constants.managerHomePageString);
		managerHomePage.clickLogoutLink();
		logoutPage.handlePopup();
	}
}