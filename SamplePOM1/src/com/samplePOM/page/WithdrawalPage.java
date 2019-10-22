package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WithdrawalPage extends BasePage{

	public WithdrawalPage(WebDriver driver){
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
}