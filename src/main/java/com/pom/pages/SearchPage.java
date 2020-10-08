package com.pom.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class SearchPage extends BasePage{
	
	public static String Searchtitle=null;
	
	private By Searchbox=By.xpath("//*[contains(@data-qa-id, 'omni-searchbox-keyword')]");
	private By localitybox=By.xpath("//*[contains(@data-qa-id, 'omni-searchbox-locality')]");
	private By SearchButton=By.xpath("//*[@class='c-omni-suggestion-item__content__title' and text()='Hospital']");
	private By locationButton=By.xpath("//div[@class='c-omni-suggestion-item__content__title' and text()='Kolkata']");

	public SearchPage(WebDriver driver) {
		super(driver);
	
	}

	public WebElement getSearchbox() {
		return getElement(Searchbox);
	}
	
	public WebElement getLocalitybox() {
		return getElement(localitybox);
	}
	
	public WebElement getSearchButton() {
		waitforElement(SearchButton);
		return getElement(SearchButton);
	}
	
	public WebElement getLocationButton() {
		waitforElement(locationButton);
		return getElement(locationButton);
	}

	
	public String getSearchPageTitle() {
		return PageTitle();
	}


	public void dosearch(String search,String location){
		
		getLocalitybox().clear();
		getLocalitybox().sendKeys(location);
		getLocationButton().click();
		getSearchbox().sendKeys(search);
		getSearchButton().click();
		Searchtitle=getSearchPageTitle();
		System.out.println("Search Page Title-->"+Searchtitle);
		

}

	




	
}
