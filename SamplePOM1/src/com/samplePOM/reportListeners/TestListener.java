package com.samplePOM.reportListeners;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestListener implements ITestListener {

	public static Hashtable<String, String> reportFiles = new Hashtable<String, String>();
	public static boolean flag = true;
	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		String resultFolderPath = System.getProperty("user.dir")+File.separator+"test-output"+File.separator;
		ISuite testSuite = context.getSuite();
		XmlSuite xmlSuite = testSuite.getXmlSuite();
		List<XmlTest> xmlTest = xmlSuite.getTests();
		if(flag){
			for(XmlTest test:xmlTest){
				String reportName = test.getParameter("reportName");
				String filePath = resultFolderPath+reportName+".html";
				reportFiles.put(reportName.toLowerCase(), filePath);
			}
			flag = false;
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
