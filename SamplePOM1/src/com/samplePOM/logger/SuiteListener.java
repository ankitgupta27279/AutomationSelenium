package com.samplePOM.logger;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener{

	public static String Log4jPath;
	
	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite arg0) {
		String projectPath = System.getProperty("user.dir");
		Log4jPath = projectPath+File.separator+"runtime"+File.separator+"log4j.properties";
		PropertyConfigurator.configure(Log4jPath);
		//Create a new folder where we can put all logs
		String logDir = projectPath+File.separator+"test-output"+File.separator+"logs";
		File file = new File(logDir);
		if(file.getAbsoluteFile().exists()){
			try{
				FileUtils.forceDelete(file);
			}catch(IOException e){
				e.printStackTrace();
			}
			file.mkdir();
			return;
		}
		file.mkdir();
	}

}
