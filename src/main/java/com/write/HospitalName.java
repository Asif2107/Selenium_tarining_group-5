package com.write;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class HospitalName extends BasePage {

	private By hospweb=By.xpath("//h2[@data-qa-id='hospital_name']");
	
	public HospitalName(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public List<WebElement> getHospitalsName() {
		waitforElement(hospweb);
		return getElements(hospweb);
		
	}
	
	
	
	public List<String> showHospitalName()
	{
		List<String> hname=null;
		waitforElement(hospweb);
		List<WebElement> hweb=null;
		try {
			hweb=getHospitalsName();
			 hname=new ArrayList<String>();
			
			for(WebElement l:hweb)
			{
				try {
					hname.add(l.getText());
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					hname.add(l.getText());
				}
			}
			
			for(String s:hname)
			{
				System.out.println(s);
			}
			return hname;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hname;
	}
	
	

}
