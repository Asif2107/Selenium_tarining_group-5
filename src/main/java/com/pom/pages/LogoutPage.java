package com.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class LogoutPage extends BasePage {
	
	private By BacktoHome= By.xpath("//*[@class='practo-logo']/descendant::i[@class='practo_logo_new']");
	private By DropeDown= By.xpath("//span[@class='up-triangle']/descendant::span[@class='downarrow icon-ic_down_cheveron']");
	private By LogoutButton= By.xpath("//*[@class=\"u-d nav-dropdown text-left active-state\"]/descendant::a[@class='nav-interact logout-click']");

	public LogoutPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getBacktoHome() {
		waitforElement(BacktoHome);
		return getElement(BacktoHome);
	}

	public WebElement getDropeDown() {
		waitforElement(DropeDown);
		return getElement(DropeDown);
	}

	public WebElement getLogoutButton() {
		waitforElement(LogoutButton);
		return getElement(LogoutButton);
	}

	public void logOut() {
		
		getBacktoHome().click();
		waitforElement(LogoutButton);
		getDropeDown().click();
		waitforElement(LogoutButton);
		getLogoutButton().click();
		
	}

	

}
