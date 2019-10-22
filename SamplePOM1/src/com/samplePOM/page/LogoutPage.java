package com.samplePOM.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends BasePage{
	
	public LogoutPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void handlePopup(){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}	
}