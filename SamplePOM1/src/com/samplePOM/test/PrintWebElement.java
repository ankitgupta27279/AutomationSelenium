package com.samplePOM.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PrintWebElement {

	@Test
	public void printWebElement(){
		//System.setProperty("webdriver.gecko.driver", "D:\\soft\\jars\\webdrivers_driver\\geckodriver-v0.16.1-win32\\geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "D:\\soft\\jars\\webdrivers_driver\\cd_25092016\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.wikipedia.org/");
		//WebElement ele = driver.findElement(By.id("searchInput2"));
		/*WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("searchInput2"))));*/
		
		try{
			new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver d){
					return d.findElement(By.id("searchInput2"));
				}
			});
		}catch(Exception e){
			System.out.println("in catch");
			e.printStackTrace();
		}
		
		
		//driver.findElement(By.id("searchInput")).sendKeys("jal");
	}
}