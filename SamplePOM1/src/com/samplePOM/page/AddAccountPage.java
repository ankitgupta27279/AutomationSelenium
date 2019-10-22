package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddAccountPage  extends BasePage{
	
	public AddAccountPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="cusid")
	WebElement custID;
	
	@FindBy(name="selaccount")
	WebElement accountType;
	
	@FindBy(name="inideposit")
	WebElement initialDeposit;
	
	@FindBy(name="button2")
	WebElement submitBTN;
	
	@FindBy(name="reset")
	WebElement resetBTN;
	
	public void enterCustID(String custID){
		this.custID.sendKeys(custID);
	}
	
	public void selectAccountType(String accountType){
		Select actSel = new Select(this.accountType);
		actSel.selectByVisibleText(accountType);
	}
	
	public void enterInitialDeposit(String intialDeposit){
		this.initialDeposit.sendKeys(intialDeposit);
	}
	
	public void clickSumitBTN(){
		submitBTN.click();
	}
	
	public void clickResetBTN(){
		resetBTN.click();
	}
}