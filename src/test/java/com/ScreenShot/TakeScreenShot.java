package com.ScreenShot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class TakeScreenShot {
	
	//public Page page;
	static Calendar  currentDate = Calendar.getInstance();
	static SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MMM-dd HH-MM-SS");
	static String dateNow = formatter.format(currentDate.getTime());
	
	public static void FailScreenShot(String tc,WebDriver driver) {
		  System.out.println("Test Case Name = "+tc);
		  File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      try {
	    	  FileUtils.copyFile(scr, new File(".\\Screenshot\\Failed_Test\\"+tc+dateNow+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	      //page=new BasePage(driver);
	}

	public static void PassScreenShot(String tc, WebDriver driver) {	
		  File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      try {
	    	  FileUtils.copyFile(scr, new File(".\\Screenshot\\Passed_Test\\"+tc+dateNow+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}	
		
	}

}
