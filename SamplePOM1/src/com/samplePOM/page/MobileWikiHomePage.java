package com.samplePOM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileWikiHomePage extends BasePage{
	
	WebDriver driver = null;
	public MobileWikiHomePage(WebDriver driver){
		super(driver);
		this.driver = driver;
	}

	public By wikiTextOnHomePage = By.cssSelector("body[id='www-wikipedia-org']>h1>div>div");
	public By searchTB = By.id("searchInput");
	public By searchBTN = By.cssSelector("form[id='search-form']>fieldset>button");
	
	public void enterTextInWikiSearchTB(String t){
		driver.findElement(searchTB).sendKeys(t);
	}
	
	public void clickSearchBTN(){
		driver.findElement(searchBTN).click();
	}
}
