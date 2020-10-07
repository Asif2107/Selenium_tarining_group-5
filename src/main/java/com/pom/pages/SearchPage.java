package com.pom.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class SearchPage extends BasePage{
	
	private By Searchbox=By.xpath("//*[contains(@data-qa-id, 'omni-searchbox-keyword')]");
	private By localitybox=By.xpath("//*[contains(@data-qa-id, 'omni-searchbox-locality')]");
	private By SearchButton=By.xpath("//*[@class='c-omni-suggestion-item__content__title' and text()='Hospital']");

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
		return getElement(SearchButton);
	}


	public void dosearch(String search,String location){
		
		getLocalitybox().clear();
		getLocalitybox().sendKeys(location);
		getSearchbox().sendKeys(search);
		getSearchButton().click();

}





	
}
