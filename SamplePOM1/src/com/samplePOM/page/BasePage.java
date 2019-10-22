package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.samplePOM.utilities.CommonUtilities;

public class BasePage {

	public WebDriver driver = null;
	public WebDriverWait wait = null;
	public CommonUtilities commonUtil = null;
	
	public BasePage(WebDriver driver){
		this.driver = driver;
		commonUtil = new CommonUtilities(driver);
	}
}