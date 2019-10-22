package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditCustomerPage extends BasePage{
	
	public EditCustomerPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="cusid")
	WebElement custIDTB;
	
	@FindBy(name="AccSubmit")
	WebElement submitBTN;
	
	@FindBy(name="res")
	WebElement resetBTN;
	
	public void enterCustID(String id){
		custIDTB.sendKeys(id);
	}
	
	public void clickSubmitButton(){
		submitBTN.click();
	}
	
	public void clickResetButton(){
		resetBTN.click();
	}
}