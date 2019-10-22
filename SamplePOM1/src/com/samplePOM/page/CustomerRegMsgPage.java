package com.samplePOM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerRegMsgPage  extends BasePage{
	
	public CustomerRegMsgPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Continue")
	WebElement continueBTN;
	
	@FindBy(xpath="//*[@id='customer']/tbody/tr[4]/td[2]")
	public static WebElement custIDEle;
	
	public static By by_custIDText = By.cssSelector("table[id='customer']>tbody>tr:nth-of-type(4)>td:nth-of-type(1)");
	public static By by_custIDEle = By.xpath("//*[@id='customer']/tbody/tr[4]/td[2]");
	
	public void clickContinueBTN(){
		continueBTN.click();
	}
	
	public String getCustID(){
		return (custIDEle.getText().toString());
	}
	
	public WebElement getCustIDElement(){
		return custIDEle;
	}
	
	public By getCustID_By(){
		return by_custIDEle;
	}
}