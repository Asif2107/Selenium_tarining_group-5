package com.pom.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class FilterPage extends BasePage {
	
	private By FilterDropdown=By.xpath("//div[@class='c-filter__top__wrapper']/child::div[4]/child::i[1]");
	private By Parking = By.xpath("//div[@class='c-filter__column u-d-inlineblock']/child::label[1]/div");
	private By Pharmacy_checkbox=By.xpath("//div[@class='c-filter__column u-d-inlineblock']/child::label[3]/div");
	private By scrollElement=By.xpath("//h2[@data-qa-id='hospital_name' and text()='A-One Hospital']");
	
	public FilterPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getFilterDropdown() {
		waitforElement(FilterDropdown);
		return getElement(FilterDropdown);
	}


	public WebElement getParking() {
		waitforElement(Parking);
		return getElement(Parking);
	}


	public WebElement getPharmacy_checkbox() {
		waitforElement(Pharmacy_checkbox);
		return getElement(Pharmacy_checkbox);
	}
	
	public void scroll() {
		//WebElement scrollElement=driver.findElement(By.xpath("//td[@id='year2020']"));
		//Actions actions = new Actions(driver);
		//actions.moveToElement(scrollElement);
		//actions.perform();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
	}
	
	public void dofilter() throws InterruptedException {
		
		getFilterDropdown().click();
		getParking().click();
		waitforElement(FilterDropdown);		
		try {
			getFilterDropdown().click();
		}catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			getFilterDropdown().click();
		}
		getPharmacy_checkbox().click();
		//scroll();
		Thread.sleep(3000);
		
	}

}
