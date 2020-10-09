package com.pom.pages;

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
	

	
	public HomePage dologin(String username, String pasw)
	{
		getSigninButton().click();
		getEmailId().sendKeys(username);
		getPassword().sendKeys(pasw);
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
		Title=getLoginPageTitle();
		
		
	}



	
	

}
