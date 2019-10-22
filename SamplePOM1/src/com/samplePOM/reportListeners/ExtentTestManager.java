package com.samplePOM.reportListeners;

import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {

	public static ThreadLocal<ExtentTest> extentTestManager = new ThreadLocal<>();
	
	public static void setExtentTest(ExtentTest rep){
		extentTestManager.set(rep);
	}
	
	public static ExtentTest getExtentTest(){
		return extentTestManager.get();
	}
}