package com.samplePOM.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MiniStatementPage extends BasePage{

	WebDriver driver = null;
	public MiniStatementPage(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	public static By by_txt_MiniStatementForm = By.cssSelector("table[align='center']>tbody>tr:nth-of-type(1)>td>p");//Mini Statement Form
	public static By by_txt_LastFiveTransDetails = By.cssSelector("table[align='center']>tbody>tr:nth-of-type(1)>td>p");//Last Five Transaction Details for Account No: 43156
	By by_AccountNoTB = By.name("accountno");
	By by_SubmitBTN = By.name("AccSubmit");
	By by_ResetBTN = By.name("res");
	By by_ContinueBTN = By.linkText("Continue");
	public static String txt_MiniStatementForm = "Mini Statement Form";
	
	public void enterAccountNo(String accountNo){
		driver.findElement(by_AccountNoTB).sendKeys(accountNo);
	}
	
	public void clickSubmitBTN(){
		driver.findElement(by_SubmitBTN).click();
	}
	
	public void clickContinueBTN(){
		driver.findElement(by_ContinueBTN).click();
	}
}