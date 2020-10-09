package com.pom.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class loginPage extends BasePage{
	//locators
	public static String Title=null;
	private By emailId = By.id("username");
	private By password =By.id("password");
	private By loginButton=By.id("login");
	private By SigninButton=By.xpath("//a[contains(@class, 'btn-border nav-login nav-interact ')]");

	Properties property;
	
	public loginPage(WebDriver driver) {
		super(driver);
	}

	//getters(encapsulation)
	
	public WebElement getSigninButton() {
		return getElement(SigninButton);
	}
	
	public WebElement getEmailId() {
		return getElement(emailId);
	}

	
	public  WebElement getPassword() {
		return getElement(password);
	}

	
	public  WebElement getLoginButton() {
		return getElement(loginButton);
	}

	
	public String getLoginPageTitle() {
		return PageTitle();
	}
	

	
	public HomePage dologin_cred()
	{
		getSigninButton().click();
		Title=getLoginPageTitle();
		System.out.println("Title:-"+Title);
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fs);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		getEmailId().sendKeys(property.getProperty("username"));
		getPassword().sendKeys(property.getProperty("password"));
		getLoginButton().click();
		
		return getInstance(HomePage.class);
		
	}
	
	//@overloading
	public void dologin()
	{
		getSigninButton().click();
		getEmailId().sendKeys("");
		getPassword().sendKeys("");
		getLoginButton().click();
		
	}



	
	

}
