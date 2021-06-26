package testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProviderFactory.DataProviderFactory;
import helper.BaseClass;
import pages.Login;
import pages.UserManagement;

public class UserManagementTest extends BaseClass {
	
	Login login;
	UserManagement user;
	
	@Test(dataProvider="Sheet2", description = "Testing the Add User Button", priority=0)
	
	public void UMTest(String role, String name, String username, String status, String pass ) {
		
		login = PageFactory.initElements(driver, Login.class);
		user = PageFactory.initElements(driver, UserManagement.class);	
		
		//adduser = PageFactory.initElements(driver, Adduser.class);
		
		logger = report.createTest("UM Test");
		
		login.enterUserName("admin"); 
		logger.info("User enter username");
		
		login.enterPassword("admin123");
		logger.info("user entered password");
		
		login.clickLoginButton();
		logger.info("user clicked login");
		
		user.ValidatePage();
		logger.info("user validated page");
		
		user.clickUserTab();
		logger.info("user hovered and clicked user tab");
		
		user.clickAddButton();
		logger.info("User clicked add button");
		
		user.selectRole(role);
		logger.info("User selected role");
		
		user.enterempName(name);
		logger.info("user entered employee name");
		
		user.enterUsername(username);
		logger.info("User entered username");
		
		user.selectStatus(status);
		logger.info("User selected status");
		
		user.enterPassword(pass);
		logger.info("user entered and confirmed password");
		
		user.clickSave();
		logger.info("User clicked save");
		
		
				
	}
	
	@DataProvider(name="Sheet2")
	  public Object[][] getDataFromSources() throws FileNotFoundException, IOException
	  {
		  System.out.println("LOG: INFO Running Data Provider first to generate data");
		  
		  int rows = DataProviderFactory.getExcel().getRows("Sheet2");		// Gives us the total number of rows
		  
		  int columns = DataProviderFactory.getExcel().getColumns("Sheet2");	// gives us the total number of columns
		  
		  System.out.println("Total number of rows: " + rows);
		  System.out.println("Total number of columns: " + columns );
		  
		  Object [][] arr = new Object [rows - 1] [columns]; 							// or you can write [rows-1] [columns] // basically 1 row and 2 columns
		  
		  // navigation requires loops
		  for(int i=0; i<rows-1; i++)										// rows-1 so we can read 2nd row 
		  {
			  for(int j=0; j<columns; j++)
			  {
				  arr[i][j] = DataProviderFactory.getExcel().getCellData("Sheet2", i+1, j);			// i+1 because we dont want to use the first row. In order to minimize the spill over
			  }																					    // we are also doing rows-1,  otherqise the i will throw array of of bound error.
		  }
		  
		  System.out.println("LOG: INFO Data Provider is ready to use");
		  return arr;
	  }

}
