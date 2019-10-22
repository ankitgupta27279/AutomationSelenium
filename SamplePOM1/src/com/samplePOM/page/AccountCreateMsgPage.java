package com.samplePOM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreateMsgPage extends BasePage{
	
	public AccountCreateMsgPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="table[id='account']>tbody>tr:nth-of-type(4)>td:nth-of-type(2)")
	public static WebElement accountIDEle;
	
	@FindBy(linkText="Continue")
	WebElement continueBTN;
	
	public static By by_actIDText = By.cssSelector("table[id='account']>tbody>tr:nth-of-type(4)>td:nth-of-type(1)");
	public static By by_actIDEle = By.xpath("//*[@id='account']/tbody/tr[4]/td[2]");
	
	public void clickContinueBTN(){
		continueBTN.click();
	}
	
	public String getAccountID(){
		return (accountIDEle.getText().toString());
	}
	
	public WebElement getAccountIDElement(){
		return accountIDEle;
	}
}