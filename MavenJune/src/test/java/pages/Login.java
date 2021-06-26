package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helper.utility;

public class Login {

	WebDriver driver;
	
	public Login(WebDriver d)
	{
		this.driver = d;
	}
	
	@FindBy(id = "txtUsername")WebElement username;							// WebElement username =  driver.findBy(id = "txtUsername" );
	@FindBy(id = "txtPassword")WebElement password;							// WebElement password = driver.findBy(id = "txtPassword" ); 
	@FindBy(xpath = "//input[@value='LOGIN']") WebElement loginButton;		// WebELement loginButton = driver.findBy(xpath = "//input[@value='LOGIN']");
	
	//1st we need to validate homepage, already have a method in utility class
	public void ValidateHomePage()
	{
		utility.validateTitle(driver, "OrangeHRM");							//vaildating title 
	}
	
	//2nd test enter username
	public void enterUserName(String s)
	{
		System.out.println("LOG:INFO Username entered");
		utility.waitForWebElement(driver, username).sendKeys(s); 			//waiting for username element to be visible and interactive before sending info
	}
	
	public void enterPassword(String p)
	{
		System.out.println("LOG:INFO Password entered");
		utility.waitForWebElement(driver, password).sendKeys(p);			//waiting for password element to be visible and interactive before sending info
	}
	
	public void clickLoginButton()
	{
		utility.waitForWebElement(driver, loginButton).click();
		System.out.println("LOG:INFO Clicked on login Button");
	}
	
	
	
}
