package com.samplePOM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerHomePage extends BasePage{
	
	public ManagerHomePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Manager")
	WebElement managerLink;
	
	@FindBy(linkText="New Customer")
	WebElement newCustomerLink;
	
	@FindBy(linkText="Edit Customer")
	WebElement editCustomerLink;
	
	@FindBy(linkText="Delete Customer")
	WebElement deleteCustomerLink;
	
	@FindBy(linkText="New Account")
	WebElement newAccountLink;
	
	@FindBy(linkText="Edit Account")
	WebElement editAccountLink;
	
	@FindBy(linkText="Delete Account")
	WebElement deleteAccountLink;
	
	@FindBy(linkText="Deposit")
	WebElement depositLink;
	
	@FindBy(linkText="Fund Transfer")
	WebElement fundTransferLink;
	
	@FindBy(linkText="Log out")
	WebElement logoutLink;
	
	@FindBy(linkText="Balance Enquiry")
	WebElement balanceEnquiry;
	
	@FindBy(linkText="Mini Statement")
	WebElement miniStatement;
	
	@FindBy(xpath="//*[contains(text(), 'Manger Id')]")
	public static WebElement managerIDText;
	
	public static By by_managerIDTxt = By.xpath("//*[contains(text(), 'Manger Id')]");
	
	public void clickBalanceEnquiryLink(){
		balanceEnquiry.click();
	}
	
	public void clickMiniStatementLink(){
		miniStatement.click();
	}
	
	public void clickManagerLink(){
		managerLink.click();
	}
	
	public void clickNewCustomerLink(){
		newCustomerLink.click();
	}
	
	public void clickEditCustomerLink(){
		editCustomerLink.click();
	}
	
	public void clickDeleteCustomerLink(){
		deleteCustomerLink.click();
	}
	
	public void clickNewAccountLink(){
		newAccountLink.click();
	}
	
	public void clickEditAccountLink(){
		editAccountLink.click();
	}
	
	public void clickDeleteAccountLink(){
		deleteAccountLink.click();
	}
	
	public void clickDepositLink(){
		depositLink.click();
	}
	
	public void clickFundTransferLink(){
		fundTransferLink.click();
	}
	
	public void clickLogoutLink(){
		logoutLink.click();
	}
	
	public String getManagerID(){
		return (managerIDText.getText().toString());
	}
}