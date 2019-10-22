package com.samplePOM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepositPage extends BasePage{

	public DepositPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountno")
	WebElement accountNo;
	
	@FindBy(name="ammount")
	WebElement amount;
	
	@FindBy(name="desc")
	WebElement description;
	
	@FindBy(name="AccSubmit")
	WebElement submitBTN;
	
	@FindBy(name="res")
	WebElement resetBTN;
	
	@FindBy(id="deposit")
	WebElement depositTable;
	
	@FindBy(linkText="Continue")
	WebElement continueBTN;
	
	public static By by_TransactionDetails = By.cssSelector("table[id='deposit']>tbody:nth-of-type(1)>tr:nth-of-type(1)>td:nth-of-type(1)>p");
	
	public void enterAccountNo(String accountNo){
		this.accountNo.sendKeys(accountNo);
	}
	
	public void enterAmount(String amount){
		this.amount.sendKeys(amount);
	}
	
	public void enterDescription(String description){
		this.description.sendKeys(description);
	}
	
	public void clickSubmitBTN(){
		submitBTN.click();
	}
	
	public void clickResetBTN(){
		resetBTN.click();
	}
	
	public WebElement getDepositTable(){
		return depositTable;
	}
	
	public void clickContinueBTN(){
		continueBTN.click();
	}
}