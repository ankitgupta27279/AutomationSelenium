package com.samplePOM.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.samplePOM.page.WikiHomePage;

public class HomePageTest extends BaseTest{

	private WikiHomePage wikiHomePage = null;
	
	@BeforeTest
	public void initConstr(){
		wikiHomePage = new WikiHomePage(driver);
	}
	
	/*@Test(priority=0)
	public void launchURL(){
		driver.get("http://www.wikipedia.org/");
	}*/
	
	@Test(priority=0)
	public void searchText(){
		wikiHomePage.enterTextInSearchTB();
	}
}