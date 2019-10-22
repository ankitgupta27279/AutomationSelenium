package com.samplePOM.reportListeners;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportManager {

	public static ThreadLocal<ExtentReports> extentReportManager = new ThreadLocal<>();
	
	public static void setExtentReport(ExtentReports rep){
		extentReportManager.set(rep);
	}
	
	public static ExtentReports getExtentReport(){
		return extentReportManager.get();
	}
}