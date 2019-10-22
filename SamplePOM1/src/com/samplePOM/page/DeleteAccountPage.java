package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage extends BasePage{
	
	public DeleteAccountPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountno")
	WebElement accountNo;
	
	@FindBy(name="AccSubmit")
	WebElement submitBTN;
	
	@FindBy(name="res")
	WebElement resetBTN;
	
	public void enterAccountNo(String accountNo){
		this.accountNo.sendKeys(accountNo);
	}
	
	public void clickSubmitBTN(){
		submitBTN.click();
	}
	
	public void clickResetBTN(){
		resetBTN.click();
	}
}
