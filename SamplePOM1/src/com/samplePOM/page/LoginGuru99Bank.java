package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginGuru99Bank extends BasePage{
	
	public LoginGuru99Bank(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[name='uid']")
	WebElement userIDTB;
	
	@FindBy(css="input[name='password']")
	WebElement pwdTB;
	
	@FindBy(css="input[name='btnLogin']")
	WebElement loginBTN;
	
	@FindBy(css="input[name='btnReset']")
	WebElement resetBTN;
	
	public void enterUserName(String userID){
		//userIDTB.click();
		userIDTB.sendKeys(userID);
	}
	
	public void enterPassword(String password){
		pwdTB.sendKeys(password);
	}
	
	public void clickLoginBtn(){
		loginBTN.click();
	}
	
	public void clickResetBtn(){
		resetBTN.click();
	}
}