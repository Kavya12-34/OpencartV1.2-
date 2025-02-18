package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import java.net.*;

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
	public static WebDriver driver;	//static keyword used to use driver object across all classes
	public Logger logger;//log4j 
	public Properties p;
	
	  @BeforeClass(groups={"Sanity","Regression","Master","DataDriven"})
	  @Parameters({"os","browser"})
	  public void setup(String os,String br ) throws InterruptedException, IOException {
		  FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		  p=new Properties();
		  p.load(file);
		  
		  logger = LogManager.getLogger(this.getClass()); //get class dynamically
		  
		//Execution environment = remote
		  if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		  {
			DesiredCapabilities cap = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
			cap.setPlatform(Platform.WIN11);
		  }
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
		  
			//browser
			switch(br.toLowerCase())
			  {
			  case "chrome":cap.setBrowserName("chrome");break;
			  case "edge": cap.setBrowserName("MicrosoftEdge");break;
			  case "firefox": cap.setBrowserName("firefox");break;
			 default: System.out.println("No matching browser"); return;
			  }
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		  }
		  
		  //Execution environment = local
		  if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		  {
		  switch(br.toLowerCase())
		  {
		  case "chrome": driver= new ChromeDriver();break;
		  case "edge": driver= new EdgeDriver();break;
		  case "firefox": driver= new FirefoxDriver();break;
		  default: System.out.println("invalid browser"); return;
		  }
		  System.out.println(os);
		  }
		  driver.manage().deleteAllCookies();
		  
		  driver.get(p.getProperty("URL")); //getting url from properties file
		  driver.manage().window().maximize();
		  Thread.sleep(2000);
	  }

	@AfterClass(groups={"Sanity","Regression","Master","DataDriven"})
	  public void teardown() {
		  driver.quit();
	  }
	 
	 public String randomstring()
	  {
		String generatedString = RandomStringUtils.randomAlphabetic(6);
		return generatedString;
	  }
	  
	  public String randomnumber()
	  {
		String generatedno = RandomStringUtils.randomNumeric(10);
		return generatedno;
	  }
	  
	  public String alphanumeric()
	  {
		String pass = RandomStringUtils.randomAlphanumeric(8);
		return pass;
	  }
	  
	  public String captureScreen(String tname)
	  {
		  String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		  TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		  File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		  
		  String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"-"+timeStamp+".png";
		  File targetFile = new File(targetFilePath);
		  sourceFile.renameTo(targetFile);
		  return targetFilePath;
		 
	  
	  }

}
