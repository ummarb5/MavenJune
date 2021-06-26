package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class browserFactory {

	static WebDriver driver;					// not adding chrome driver because it will run whatver driver we want to use

	public static WebDriver StartBrowser(String browserName, String url)
	{
		if(browserName.equalsIgnoreCase("Firefox"))					// ignores capital or no capital, will ignore 
		{
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.get(url);
		
		return driver;
	}
	
	
	
}
