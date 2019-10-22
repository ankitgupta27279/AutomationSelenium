package com.samplePOM.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiHomePage extends BasePage{

	public WikiHomePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="searchInput")
	WebElement searchTB;
	
	public void enterTextInSearchTB(){
		searchTB.sendKeys("agni");
	}
	
	/*public WikiHomePage(WebDriver driver){
		super(driver);
	}
	
	By searchTb = By.id("searchInput");
	
	public void enterTextInSearchTB(){
		driver.findElement(searchTb).sendKeys("vayu");
	}*/
}