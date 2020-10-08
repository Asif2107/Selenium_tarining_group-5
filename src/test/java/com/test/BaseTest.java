package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.BasePage;
import com.pages.Page;
import com.pom.pages.FilterPage;
import com.pom.pages.HomePage;
import com.pom.pages.SearchPage;
import com.pom.pages.loginPage;
import com.write.HospitalName;
import com.write.WriteData;



public class BaseTest {
	WebDriver driver;
	public Page page;
	List<String> name=null;
	
	@BeforeTest(description = "Opening Browser")
	public void createDriver()
	{
		
			
			String ChromePath = "C:\\Users\\HP\\eclipse-workspace\\Practo_Automation\\Driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", ChromePath );
		    driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.get("https://www.practo.com/");
			try {
			Thread.sleep(3000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			page=new BasePage(driver);
			
			
		
		
	}
	
	@Test(priority=1)
	public void verifyHomepagePageTest() {
		String title=page.getInstance(HomePage.class).getHomePageTItle();
		String header=page.getInstance(HomePage.class).getHomePageHeader();
		System.out.println("Title:-"+title);
		System.out.println(header);
		Assert.assertEquals(header, "How Practo is Helping India Fight COVID-19");
	}

	
	@Test(priority=2)
	public void doLoginTest() {

		page.getInstance(loginPage.class).dologin("9903723035", "Ranjan@2020");
		//page.getInstance(loginPage.class).dologin(" ", "");
		String title=page.getInstance(loginPage.class).getLoginPageTitle();
		Assert.assertEquals(title, "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
	}
	
	@Test(priority=3)
	public void searchtest(){
		page.getInstance(SearchPage.class).dosearch("Hospital","Kolkata");
		page.getInstance(SearchPage.class);
		String SearchPageTitle=SearchPage.Searchtitle;
		Assert.assertEquals(SearchPageTitle, "Best Hospitals in Kolkata - Book Appointment Online, View Fees, Reviews | Practo");
		
	}
	
	@Test(priority=4)
	public void FilterSearch() throws Exception{
		page.getInstance(FilterPage.class).dofilter();
	}
	
	@Test(priority=5)
	public void getNames()
	{
		name=page.getInstance(HospitalName.class).showHospitalName();
	}
	
	@Test(priority=6)
	public void writeExcel()throws FileNotFoundException, IOException 
	{
		page.getInstance(WriteData.class).doWrite(name);
	}

		
	@AfterTest
	public void tearDown() {
		driver.quit();
	
	}
	

}
