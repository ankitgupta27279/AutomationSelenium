package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundTransferPage extends BasePage{
	
	public FundTransferPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="payersaccount")
	WebElement payerActNoTB;
	
	@FindBy(name="payeeaccount")
	WebElement payeeActNoTB;
	
	@FindBy(name="ammount")
	WebElement amountTB;
	
	@FindBy(name="desc")
	WebElement descTB;
	
	@FindBy(name="AccSubmit")
	WebElement submitBTN;
	
	@FindBy(name="res")
	WebElement resetBTN;
	
	public void enterPayerAccountNo(String payerAccountNo){
		payerActNoTB.sendKeys(payerAccountNo);
	}
	
	public void enterPayeeAccountNo(String payeeAccountNo){
		payeeActNoTB.sendKeys(payeeAccountNo);
	}
	
	public void enterAmount(String amount){
		amountTB.sendKeys(amount);
	}
	
	public void enterDescription(String desc){
		descTB.sendKeys(desc);
	}
	
	public void clickSubmitBTN(){
		submitBTN.click();
	}
	
	public void clickResetBTN(){
		resetBTN.click();
	}
}