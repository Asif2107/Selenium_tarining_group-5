package com.pages;

import java.util.List;

//https://github.com/Asif2107/Selenium_tarining_group-5.git
//git@github.com:Asif2107/Selenium_tarining_group-5.git
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page{

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String PageTitle() {
		return driver.getTitle();
	}

	@Override
	public String Header(By location) {
		// TODO Auto-generated method stub
		return getElement(location).getText();
	}

	@Override
	public WebElement getElement(By location) {
		WebElement element= null;
		try {
			element=driver.findElement(location);
			return element;
		}catch(Exception e) {
			System.out.println("error occurred while finding elements"+location.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	

	@Override
	public void waitforElement(By location) {
		try {
			//wait.until(ExpectedConditions.presenceOfElementLocated(location));
			wait.until(ExpectedConditions.visibilityOfElementLocated(location));
		}catch(Exception e) {
			System.out.println("error occurred while waiting for elements"+location.toString());
			e.printStackTrace();
		}
		
	}

	@Override
	public void waitforPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		}catch(Exception e) {
			System.out.println("error occurred while waiting");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<WebElement> getElements(By location) {
		List<WebElement> hweb=null;
		try {
			hweb=driver.findElements(location);
			return hweb;
		}
		catch(Exception e) {
			System.out.println("error occurred while finding elements"+location.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	

}
