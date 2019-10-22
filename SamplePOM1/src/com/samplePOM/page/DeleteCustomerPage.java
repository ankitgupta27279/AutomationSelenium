package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteCustomerPage extends BasePage{

	public DeleteCustomerPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="cusid")
	public WebElement custID;
	
	@FindBy(name="AccSubmit")
	public WebElement submitBTN;
	
	@FindBy(name="res")
	public WebElement resetBTN;
	
	public void enterCustID(String custID){
		this.custID.sendKeys(custID);
	}
	
	public void clickSubmitBTN(){
		submitBTN.click();
	}
	
	public void clickResetBTN(){
		resetBTN.click();
	}
}
