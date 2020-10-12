package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	
	 protected WebDriver driver;
	 WebDriverWait wait;
	
	public Page(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(this.driver,20);
	}
	
	//abstract methods
	public abstract String PageTitle();
	
	public abstract String Header(By location);
	
	public abstract WebElement getElement(By location);
	
	public abstract List<WebElement> getElements(By location);
	
	public abstract void waitforElement(By location);
	
	
	public abstract void waitforPageTitle(String title);
	
	
	//java generic
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass){
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
