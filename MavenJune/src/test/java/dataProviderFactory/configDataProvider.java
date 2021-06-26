package dataProviderFactory;									// this class will read the properties from the config properties class
																			// loading the 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configDataProvider {

	Properties pro;
	
	public configDataProvider() throws Throwable, IOException {
		
		pro = new Properties();									//Initializing the properties object so we can use property features 
		pro.load(new FileInputStream(new File(System.getProperty("user.dir") + "/configs/config.properties")));
		/*
		 * basically following in one single line
		 * File srcf = new File("src/environment.properties");	
		 * FileInputStream srcIS = new FileInputStream(srcf);		
		 * pro.load(srcIS);		
		 */
	}
	
	public String getStagingURL()
	{
		return pro.getProperty("stagingURL");					// return the staging url from the config.properties file
	}
	
	public String getBrowser()
	{
		return pro.getProperty("browser");						// returns the browser from the config.properties file
	}
	
}
