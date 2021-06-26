package testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProviderFactory.DataProviderFactory;
import helper.BaseClass;
import helper.utility;
import pages.Login;
import pages.Logout;

public class LoginTest extends BaseClass {
	
	Login login;
	
	Logout lout;
	
  @Test(description = "Log in to OrangeHRM", priority=0)
  public void LoginHRM() {
	  
	  login = PageFactory.initElements(driver, Login.class); 			// the same as    Login login = new Login(driver);
	  lout = PageFactory.initElements(driver, Logout.class);				// Logout lout = new Logout(driver);
	  
	  logger = report.createTest("Login Test for OrangeHRM");
	  
	  login.ValidateHomePage(); 										// 1st test we will run
	  
	  logger.pass("Home Page Vailidated");
	  
	  login.enterUserName("admin");										
	  
	  logger.info("Username entered");
	  
	  login.enterPassword("admin123");										
	  
	  logger.info("Password Entered");
	  
	  login.clickLoginButton();
	  
	  logger.info("Clicked on Login Button");
	  
	  lout.clickLogout();
	  
	  logger.info("Clicked on the Logout button");
	  
	  
	  }
  
  @Test(dataProvider = "Sheet1", description = "Log in to OrangeHRM using Excel", priority=1)
  public void LoginHRMExcel(String uname, String pass)
  {
	  login = PageFactory.initElements(driver, Login.class); 			// the same as    Login login = new Login(driver);
	  lout = PageFactory.initElements(driver, Logout.class);				// Logout lout = new Logout(driver);
	  
	  logger = report.createTest("Login Test for OrangeHRM");
	  
	  login.ValidateHomePage(); 										// 1st test we will run
	  
	  logger.pass("Home Page Vailidated");
	  
	  login.enterUserName(uname);										// for excel reading , providing the username using excel data
	  
	  logger.info("Username entered");
	  
	  login.enterPassword(pass);										// for excel reading , providing the password using excel data
	  
	  logger.info("Password Entered");
	  
	  login.clickLoginButton();
	  
	  logger.info("Clicked on Login Button");
	  
	  //utility .captureScreenshot(driver);						//capture a screenshot before 
	  
	  lout.clickLogout();
	  
	  logger.info("Clicked on the Logout button");  
  }
  
  @DataProvider(name="Sheet1")
  public Object[][] getDataFromSources() throws FileNotFoundException, IOException
  {
	  System.out.println("LOG: INFO Running Data Provider first to generate data");
	  
	  int rows = DataProviderFactory.getExcel().getRows("Sheet1");		// Gives us the total number of rows
	  
	  int columns = DataProviderFactory.getExcel().getColumns("Sheet1");	// gives us the total number of columns
	  
	  System.out.println("Total number of rows: " + rows);
	  System.out.println("Total number of columns: " + columns );
	  
	  Object [][] arr = new Object [1] [2]; 							// or you can write [rows-1] [columns] // basically 1 row and 2 columns
	  
	  // navigation requires loops
	  for(int i=0; i<rows-1; i++)										// rows-1 so we can read 2nd row 
	  {
		  for(int j=0; j<columns; j++)
		  {
			  arr[i][j] = DataProviderFactory.getExcel().getCellData("Sheet1", i+1, j);			// i+1 because we dont want to use the first row. In order to minimize the spill over
		  }																					    // we are also doing rows-1,  otherqise the i will throw array of of bound error.
	  }
	  
	  System.out.println("LOG: INFO Data Provider is ready to use");
	  return arr;
  }
  
 }
