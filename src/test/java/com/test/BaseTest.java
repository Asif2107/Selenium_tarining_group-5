
package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ScreenShot.CustomListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.excelReadWrite.HospitalName;
import com.excelReadWrite.WriteData;
import com.pages.BasePage;
import com.pages.Page;
import com.pom.pages.FilterPage;
import com.pom.pages.HomePage;
import com.pom.pages.LogoutPage;
import com.pom.pages.SearchPage;
import com.pom.pages.loginPage;
import org.apache.log4j.Logger;

@Listeners(CustomListener.class)
public class BaseTest {
	
	
	public static WebDriver driver;
	public Page page;
	List<String> name=null;
	ExtentReports extent;
	Properties property;
	Logger log = Logger.getLogger(BaseTest.class);

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
	@Parameters("browser")
	public void createDriver(String browserName) throws IOException
	{
		

			ExtentTest test= extent.createTest("Create Driver");
						

			
			 if(browserName.equalsIgnoreCase("chrome"))
			{
				String ChromePath = "D:\\Selenium_tarining_group-5-master\\Chrome_Driver\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", ChromePath );
			
				driver = new ChromeDriver();
			}
			 else if(browserName.equalsIgnoreCase("firefox"))
				{
				 String FirefoxPath = "D:\\MOM_Data\\geckodriver.exe";
				 System.setProperty("webdriver.gecko.driver", FirefoxPath );
				driver = new FirefoxDriver();
				}
			 driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
				property = new Properties();
				property.load(fs);
				
				driver.get(property.getProperty("url"));
				log.info("Entering in application url");
				try {
				Thread.sleep(3000);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				page=new BasePage(driver);
				log.info("Entering into a homepage");
			extent.flush();
	}
	
	@Test(priority=1)
	public void verifyHomepagePageTest()
	{
		ExtentTest test= extent.createTest("Verify Home Page");
		String title=page.getInstance(HomePage.class).getHomePageTItle();
		String header=page.getInstance(HomePage.class).getHomePageHeader();
		System.out.println("Title:-"+title);
		log.info("The Title of of webpage is--->"+title);
		System.out.println(header);
		Assert.assertEquals(header, "How Practo is Helping India Fight COVID-19");
		extent.flush();
	}

	
	@Test(priority=2)
	public void doLoginTest()
	{
		ExtentTest test= extent.createTest("Login into account");
		page.getInstance(loginPage.class).dologin_cred();
		log.info("Performing the login test ");
		String title=page.getInstance(loginPage.class).getLoginPageTitle();
		Assert.assertEquals(title, "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		extent.flush();
	}
	
	@Test(priority=3)
	public void searchtest()
	{
		ExtentTest test= extent.createTest("Searching");
		page.getInstance(SearchPage.class).dosearch();
		String SearchPageTitle=SearchPage.Searchtitle;
		log.info("Searching the hospital name");
		Assert.assertEquals(SearchPageTitle, "Best Hospitals in Chennai - Book Appointment Online, View Fees, Reviews | Practo");
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
	
	@AfterClass
	public void writeExcel()throws FileNotFoundException, IOException 
	{
		ExtentTest test= extent.createTest("Write in Excel");
		log.info("Writing into a excel sheet");
		page.getInstance(WriteData.class).doWrite(name);
		extent.flush();
	}
	
	@Test(priority=6)
	public void SignOut() {
		ExtentTest test= extent.createTest("Check Out from the account");
		page.getInstance(LogoutPage.class).logOut();
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
