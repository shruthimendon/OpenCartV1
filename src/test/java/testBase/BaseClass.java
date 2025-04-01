package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Regression","Sanity","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os ,String br) throws IOException
	{
		
		//Loading properties file 
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
        	DesiredCapabilities capabilities = new DesiredCapabilities();
        	
        	//os
        	if(os.equalsIgnoreCase("windows"))
        	{
        		capabilities.setPlatform(Platform.WIN11);
        		
        	}
        	else if (os.equalsIgnoreCase("mac"))
        	{

        		capabilities.setPlatform(Platform.MAC);	
        		
        	}
        	else 
        	{
        		System.out.println("No matching os");
        		return;
        	}
        	
        	
        	//browser
        	switch(br.toLowerCase())
        	{
        	case "chrome" :capabilities.setBrowserName("chrome");break;
        	case "edge" :capabilities.setBrowserName("MicrosoftEdge");break;
        	default:System.out.println("No matchinh browser");return;
        	}
        	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hd"),capabilities);	
        	
        
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
		
		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		case "firefox" : driver =new FirefoxDriver();break;
		default:System.out.println("Invalid browser name"); return;
		}
        }	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
			
	}
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master","DataDriven"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomNumber	() {
		String generatednumber= RandomStringUtils.randomNumeric(10);
		return generatednumber;
		}

	public String randomString()
	{
		String generatedstring= RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	

	public String randomAlphaNumeric() {
		String generatedAlphanumeric= RandomStringUtils.randomAlphanumeric(6);
		return generatedAlphanumeric;
		}
	
	public String CaptureScreen(String tname) throws  IOException{
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourcefile = takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilePath = System.getProperty("user.dir")+"\\screenshots"+ tname +"_"+timeStamp +".png";
		File targetfile = new File(targetfilePath);
		sourcefile.renameTo(targetfile);
		return targetfilePath;
		
	}
}
