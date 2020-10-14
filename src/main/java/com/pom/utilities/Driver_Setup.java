package com.pom.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Driver_Setup {
	
	

	static WebDriver driver;
    public WebDriver getChromeDriver() throws IOException {
    	
    	String ChromePath = ".\\Driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChromePath );
	
		driver = new ChromeDriver();
		return driver;
    	
    }
    
    public WebDriver getEdgeDriver() throws IOException
    {
    	 String ExplorerPath = ".\\Driver\\msedgedriver.exe";
			System.setProperty("webdriver.edge.driver", ExplorerPath );
			

			 driver = new EdgeDriver();	
		return driver;
    	
    }
	

}
