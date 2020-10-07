package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.BasePage;
import com.pages.Page;
import com.pom.pages.HomePage;
import com.pom.pages.SearchPage;
import com.pom.pages.loginPage;



public class BaseTest {
	WebDriver driver;
	public Page page;

	@BeforeTest(description = "Opening Browser")
	public void createDriver()
	{
		
			
			String ChromePath = "D:\\selenem\\crome driver\\chromedriver.exe";
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

		page.getInstance(loginPage.class).dologin("8910649914", "kVFAUvmz_F$7cGR");
		//page.getInstance(loginPage.class).dologin(" ", "");
		String title=page.getInstance(loginPage.class).getLoginPageTitle();
		Assert.assertEquals(title, "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
	}
	
	@Test(priority=3)
	public void searchtest() throws InterruptedException{
		page.getInstance(SearchPage.class).dosearch("Hospital","Chenai");

		Thread.sleep(6000);		
	}
	
	

		
	@AfterTest
	public void tearDown() {
		driver.quit();
	
	}
	
	

	

}
