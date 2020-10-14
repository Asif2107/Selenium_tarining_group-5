package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ScreenShot.CustomListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.excelReadWrite.HospitalName;
import com.excelReadWrite.WriteData;
import com.pages.BasePage;
import com.pages.Page;
import com.pom.utilities.Driver_Setup;
import com.pom.utilities.FilterPage;
import com.pom.utilities.HomePage;
import com.pom.utilities.LogoutPage;
import com.pom.utilities.SearchPage;
import com.pom.utilities.loginPage;

import org.apache.log4j.Logger;

@Listeners(CustomListener.class)
public class BaseTest {
	
	public static WebDriver driver;
	public Page page;
	List<String> name=null;
	ExtentReports extent;
	Properties property;
	ExtentTest test;
	Logger log = Logger.getLogger(BaseTest.class);


	
	@BeforeClass(description = "Opening Browser")
	@Parameters("browser")
	public void createDriver(String browserName) throws IOException
	{
		String path =System.getProperty("user.dir")+"\\Reports\\ExtentReport.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Results");

		reporter.config().setDocumentTitle("Test Results");

		extent =new ExtentReports();

		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Selenium Trining Group 5");

	

	
	
	
		

			//test= extent.createTest("Create Driver","passed");
						

			
			 if(browserName.equalsIgnoreCase("chrome"))
			{
				 Driver_Setup obj = new Driver_Setup();
					
					driver=obj.getChromeDriver();
					log.info("*******Launching Chrome Driver**********");
			}
			 
			
					 
			 else if(browserName.equalsIgnoreCase("msedge"))
			 {
				 
				 Driver_Setup obj = new Driver_Setup();
					
					driver=obj.getEdgeDriver();
					log.info("*******Launching Edge Driver**********");	 
			}
			 
			
			
			 driver.manage().window().maximize();
				
				FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
				property = new Properties();
				property.load(fs);
				
				driver.get(property.getProperty("url"));
				log.info("**********Entering in application url**********");
				
				try {
				Thread.sleep(3000);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				page=new BasePage(driver);
				log.info("**********Entering into a homepage**********");
			//extent.flush();
	}
	
	@Test(priority=1, groups= {"smoke"})
	public void verifyHomepagePageTest()
	{
		test= extent.createTest("Verify Home Page","passed");
		String title=page.getInstance(HomePage.class).getHomePageTItle();
		String header=page.getInstance(HomePage.class).getHomePageHeader();
		System.out.println("Title:-"+title);
		log.info("**********The Title of of webpage is--->"+title+"**********");
		System.out.println(header);
		Assert.assertEquals(header, "How Practo is Helping India Fight COVID-19");
		//extent.flush();
	}

	
	@Test(priority=2, groups= {"smoke","regression"})
	public void doLoginTest()
	{
		test= extent.createTest("Login into account","passed");
		page.getInstance(loginPage.class).dologin_cred();
		log.info("**********Performing the login test **********");
		String title=page.getInstance(loginPage.class).getLoginPageTitle();
		Assert.assertEquals(title, "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		//extent.flush();
	}
	
	@Test(priority=3, groups= {"smoke","regression"})
	public void searchtest()
	{
		test= extent.createTest("Searching","passed");
		page.getInstance(SearchPage.class).dosearch(1);
		String SearchPageTitle=SearchPage.Searchtitle;
		log.info("**********Searching the hospital name**********");
		Assert.assertEquals(SearchPageTitle, "Best Hospitals in Delhi - Book Appointment Online, View Fees, Reviews | Practo");
		//extent.flush();
	}
	
	@Test(priority=4, groups= {"smoke","regression"})
	public void FilterSearch() throws Exception
	{
		test= extent.createTest("Apply filters","passed");
		page.getInstance(FilterPage.class).dofilter();
		log.info("**********Applying the neccessary filters**********");
		//extent.flush();
	}
	
	@Test(priority=5, groups= {"regression"})
	public void getNames()
	{
		test= extent.createTest("Get Names of Hospitals","passed");
		name=page.getInstance(HospitalName.class).showHospitalName();
		log.info("**********Getting Hospital names**********");
		//extent.flush();
	}
	
	@Test(priority=6, groups= {"regression"})
	public void writeExcel()throws FileNotFoundException, IOException 
	{
		test= extent.createTest("Write in Excel","passed");
		log.info("**********Writing into a excel sheet**********");
		page.getInstance(WriteData.class).doWrite(name);
		//extent.flush();
	}
	
	@Test(priority=7, groups= {"smoke","regression"})
	public void SignOut() {
		test= extent.createTest("Log Out from the account","passed");
		page.getInstance(LogoutPage.class).logOut();
		log.info("**********Signing Out from Account**********");
		//extent.flush();
	}
	
	@Test(priority=8, groups= {"smoke","regression"})
		public void invsearchtest()
		{
		test= extent.createTest("Searching international hospitals","failed");
			
			String SearchPageTitle=SearchPage.Searchtitle;
			log.info("**********Searching International hospital location**********");
			SoftAssert sa=new SoftAssert();
			sa.assertTrue(page.getInstance(SearchPage.class).dosearch(2));
			//sa.assertEquals(SearchPageTitle, "Best Hospitals in London - Book Appointment Online, View Fees, Reviews | Practo");
			//test.fail("Wrong hospital search");
			//Assert.assertEquals(SearchPageTitle, "Best Hospitals in London - Book Appointment Online, View Fees, Reviews | Practo");
			//extent.flush();
		}
	

		
	@AfterTest
	public void tearDown() 
	{
		//test= extent.createTest("Close Browser");
		driver.quit();
		extent.flush();
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
