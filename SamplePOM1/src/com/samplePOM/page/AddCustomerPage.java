package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage  extends BasePage{
	
	public AddCustomerPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="name")
	WebElement customerName;
	
	@FindBy(xpath="//input[@type='radio' and @name='rad1' and @value='m']")
	WebElement genderMale;
	
	@FindBy(xpath="//input[@type='radio' and @name='rad1' and @value='f']")
	WebElement genderFemale;
	
	@FindBy(id="dob")
	WebElement dob;
	
	@FindBy(name="addr")
	WebElement address;
	
	@FindBy(name="city")
	WebElement city;
	
	@FindBy(name="state")
	WebElement state;
	
	@FindBy(name="pinno")
	WebElement pinNo;
	
	@FindBy(name="telephoneno")
	WebElement telephoneNo;
	
	@FindBy(name="emailid")
	WebElement emailId;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="sub")
	WebElement submitBTN;
	
	@FindBy(name="res")
	WebElement resetBTN;
	
	public void enterCustName(String custName){
		customerName.sendKeys(custName);
	}
	
	public void clickGenderMale(){
		genderMale.click();
	}
	
	public void clickGenderFemale(){
		genderFemale.click();
	}
	
	public void enterDOB(String dob){
		this.dob.sendKeys(dob);
	}
	
	public void enterAddress(String adr){
		address.sendKeys(adr);
	}
	
	public void enterCity(String city){
		this.city.sendKeys(city);
	}
	
	public void enterState(String state){
		this.state.sendKeys(state);
	}
	
	public void enterPinNo(String pinNo){
		this.pinNo.sendKeys(pinNo);
	}
	
	public void enterMobileNo(String mobileNo){
		telephoneNo.sendKeys(mobileNo);
	}
	
	public void enterEmailID(String emailId){
		this.emailId.sendKeys(emailId);
	}
	
	public void enterPassword(String password){
		this.password.sendKeys(password);
	}
	
	public void clickSubmitBTN(){
		submitBTN.click();
	}
	
	public void clickResetBTN(){
		resetBTN.click();
	}
}