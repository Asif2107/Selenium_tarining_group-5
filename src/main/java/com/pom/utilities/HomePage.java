package com.pom.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class HomePage extends BasePage {
	
	private By header=By.xpath("//*[contains(@class, 'u-font--24 u-margin--0 u-font--bold c-header--offer')]");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public WebElement getHeader() {
		return getElement(header);
	}
	
	public String getHomePageTItle() {
		return PageTitle();
	}
	
	public String getHomePageHeader() {
		return Header(header);
	}

}
