package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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
	ExtentReports extent;
	Properties property;


	@BeforeSuite
	public void config()
	{
		String path =System.getProperty("user.dir")+"\\Reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Results");

		reporter.config().setDocumentTitle("Test Results");

		extent =new ExtentReports();

		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Rajarshee Das");

	}

	@BeforeTest(description = "Opening Browser")
	
	public void createDriver() throws IOException
	{
		

			ExtentTest test= extent.createTest("Create Driver");
			

			
			String ChromePath = "C:\\Users\\user\\Desktop\\CTS_Onboarding\\Selenium Project\\Practo_AutomationFramework\\Chrome_Driver\\chromedriver.exe";

			System.setProperty("webdriver.chrome.driver", ChromePath );
		    driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			property = new Properties();
			property.load(fs);
			
			driver.get(property.getProperty("url"));
			try {
			Thread.sleep(3000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			page=new BasePage(driver);
			extent.flush();
	}
	
	@Test(priority=1)
	public void verifyHomepagePageTest()
	{
		ExtentTest test= extent.createTest("Verify Home Page");
		String title=page.getInstance(HomePage.class).getHomePageTItle();
		String header=page.getInstance(HomePage.class).getHomePageHeader();
		System.out.println("Title:-"+title);
		System.out.println(header);
		Assert.assertEquals(header, "How Practo is Helping India Fight COVID-19");
		extent.flush();
	}

	
	@Test(priority=2)
	public void doLoginTest()
	{
		ExtentTest test= extent.createTest("Login into account");
		page.getInstance(loginPage.class).dologin("9903723035", "Ranjan@2020");
		String title=page.getInstance(loginPage.class).getLoginPageTitle();
		Assert.assertEquals(title, "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		extent.flush();
	}
	
	@Test(priority=3)
	public void searchtest()
	{
		ExtentTest test= extent.createTest("Searching");
		page.getInstance(SearchPage.class).dosearch("Hospital","Kolkata");
		page.getInstance(SearchPage.class);
		String SearchPageTitle=SearchPage.Searchtitle;
		Assert.assertEquals(SearchPageTitle, "Best Hospitals in Kolkata - Book Appointment Online, View Fees, Reviews | Practo");
		extent.flush();
	}
	
	@Test(priority=4)
	public void FilterSearch() throws Exception
	{
		ExtentTest test= extent.createTest("Apply filters");
		page.getInstance(FilterPage.class).dofilter();
		extent.flush();
	}
	
	@Test(priority=5)
	public void getNames()
	{
		ExtentTest test= extent.createTest("Get Names of Hospitals");
		name=page.getInstance(HospitalName.class).showHospitalName();
		extent.flush();
	}
	
	@Test(priority=6)
	public void writeExcel()throws FileNotFoundException, IOException 
	{
		ExtentTest test= extent.createTest("Write in Excel");
		page.getInstance(WriteData.class).doWrite(name);
		extent.flush();
	}

		
	@AfterTest
	public void tearDown() 
	{
		ExtentTest test= extent.createTest("Close Browser");
		driver.quit();
		extent.flush();
	}
	

}
