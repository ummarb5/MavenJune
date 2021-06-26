package helper;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import dataProviderFactory.DataProviderFactory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	public WebDriver driver;
	public ExtentReports report;
	public ExtentTest logger;
 
  @BeforeClass
	  public void beforeClass() throws IOException, Throwable {
	  
	  System.out.println("LOG INFO: Before suite running... setting up report");
	  	
	  	ExtentHtmlReporter reporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/reports/ETReport" + utility.getTime() + ".html"));
	  	report = new ExtentReports();
	  	report.attachReporter(reporter);
	  
	  	System.out.println("LOG INFO: After suite running...reports are ready to use");
	  
	  System.out.println("LOG INFO: Creating browser session");
	  driver = browserFactory.StartBrowser(DataProviderFactory.getConfig().getBrowser(), 
			  								DataProviderFactory.getConfig().getStagingURL());		// using start browser method 
	  
	  System.out.println("LOG INFO: Browser session created");
	  }
	  
  @AfterMethod
  public void appendReport(ITestResult result) throws IOException {
	  
	  System.out.println("Test Name " + result.getName());
	  System.out.println("LOG INFO: After method running...Generating Test Report");
	  
	  int status = result.getStatus();			// get status sends numbers 1s and 0s and that why we using int
	  
	  if(status == ITestResult.SUCCESS)
	  {
		  logger.pass("Test Scenario Passed", MediaEntityBuilder.createScreenCaptureFromPath(utility.captureScreenshot(driver)).build());
	  }
	  else if(status == ITestResult.FAILURE)
	  {
		  logger.fail("Test Failed " + result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(utility.captureScreenshot(driver)).build());
	  }
	  else if(status == ITestResult.SKIP)
	  {
		  logger.skip("Test Scenario Skipped");
	  }
	  report.flush();			//Adds it
	  
	  System.out.println("LOG INFO: After method executed...Test Result appended to report");
  }


  @AfterClass
  public void closeSession() {
	  System.out.println("LOG INFO: Closing browser session");
	  
	  driver.quit();
	  
	  System.out.println("LOG INFO: Browser session closed");
  }

}
