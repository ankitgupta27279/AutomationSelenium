package com.samplePOM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BalanceEnquiryPage  extends BasePage{
	
	public BalanceEnquiryPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public static By by_txt_BalanceEnquiryForm = By.cssSelector("table[align='center']>tbody>tr:nth-of-type(1)>td>p");//Balance Enquiry Form
	public static By by_txt_BalanceDetails = By.cssSelector("table[align='center']>tbody>tr:nth-of-type(1)>td>p");//Balance Details for Account 43156
	
	@FindBy(name="accountno")
	WebElement accountNoTB;
	
	@FindBy(name="AccSubmit")
	WebElement submitBTN;
	
	@FindBy(name="res")
	WebElement resetBTN;
	
	@FindBy(linkText="Continue")
	WebElement continueBTN;
	
	public void clickSubmitBTN(){
		submitBTN.click();
	}
	
	public void clickResetBTN(){
		resetBTN.click();
	}
	
	public void enterAccountNo(String accountNo){
		accountNoTB.sendKeys(accountNo);
	}
	
	public void clickContinueBTN(){
		continueBTN.click();
	}
}