package com.samplePOM.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities {

	public WebDriver driver = null;
	WebDriverWait wait = null;

	public CommonUtilities(WebDriver driver){
		this.driver = driver;
	}

	public void setDriver(WebDriver driver){
		if(driver == null){
			this.driver = driver;
		}
	}

	public WebDriver getDriver(){
		return driver;
	}

	public void launchURL(){
		driver.get(Constants.webURL);
	}

	public String getScreenshot(String screenshotName){
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir")+File.separator+"FailedTestsScreenshots"+File.separator+screenshotName+"_"+timeStamp+".png";
		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}

	public WebElement returnElement(By byObject){
		waitForElement(byObject, 10, 2000);
		return driver.findElement(byObject);
	}

	public void waitForElement(final By byObject, long timeInSeconds, long timeInMillis){
		try{
			new WebDriverWait(driver, timeInSeconds).until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver d){
					return d.findElement(byObject);
				}
			});
		}catch(Exception e){
			System.out.println("in catch");
		}	
	}

	public void waitForElement(final By byObject, long timeInSeconds){
		try{
			(new WebDriverWait(driver, timeInSeconds)).until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver d){
					return d.findElement(byObject);
				}
			});
		}catch(Exception e){
			System.out.println("in catch - within "+timeInSeconds+"sec element could not be found");
		}	
	}

	public boolean isElementVisible(By byObject){
		if(driver.findElements(byObject).size()>0){
			return true;
		}else{
			return false;
		}
	}

	public void waitForElementContainsText(final By by_Object, long timeInSeconds, final String text){
		try{
			(new WebDriverWait(driver, timeInSeconds)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d){
					return d.findElement(by_Object).getText().contains(text);
				}
			});
		}catch(TimeoutException timeExp){
			System.out.println("in catch - within "+timeInSeconds+" element could not be found");
			timeExp.printStackTrace();
		}catch(NoSuchElementException noSuchEleExp){
			System.out.println("in catch - this element could not be found");
			noSuchEleExp.printStackTrace();
		}
	}

	public String getTextFromElement(WebElement e){
		return e.getText().toString();
	}

	/******************************
	[[ChromeDriver: chrome on XP (dc4a62a562339c5f55955d9161351850)] -> id: searchInput]
	 *****************************/


	// return ByType of WebElement
	public By toByVal(WebElement we) {
		String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
		String locator = data[0];
		String term = data[1];

		switch (locator) {
		case "xpath":
			return By.xpath(term);
		case "css selector":
			return By.cssSelector(term);
		case "id":
			return By.id(term);
		case "tag name":
			return By.tagName(term);
		case "name":
			return By.name(term);
		case "link text":
			return By.linkText(term);
		case "class name":
			return By.className(term);
		}
		return (By) we;
	}

	/*methods to be implemented
	wait for element
	wait and then click
	wait and then enter the text in text box
	wait and double click on element
	wait and fetch the text from text box
	wait and fetch the text of an attribute
	 */
}