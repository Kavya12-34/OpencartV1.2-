package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.net.*;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;//UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test;//creating test case entries in the report and update status of test methods
	
	String repName;
	
	public void onStart(ITestContext context)   //context - test method
	{
		
	/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Date dt = new Date();
	String currentdatetimestamp=df.format(dt);*/
		
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp creation	
	repName = "Test-Report-"+timeStamp+".html";
	
	sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);//location
	sparkReporter.config().setDocumentTitle("OpenCart Automation Report");//Title of report
	sparkReporter.config().setReportName("OpenCart Functional Testing");//name of report
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	
	extent.setSystemInfo("Application", "OpenCart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	
	String os = context.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os);
	
	String browser = context.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups(); 
	if(!includedGroups.isEmpty())
	{
	 extent.setSystemInfo("Groups", includedGroups.toString());	
	}
	}
	
	public void onTestSuccess(ITestResult result) { //result - method that is passed
		test = extent.createTest(result.getTestClass().getName());//create a new entry in the report
		test.assignCategory(result.getMethod().getGroups()); //to display groups
		test.log(Status.PASS, "Test case passed is:"+result.getName());//update status p/f/s
	  }

	 
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case failed is:"+result.getName());	
		test.log(Status.FAIL, "Test case failed caused is:"+result.getThrowable());	//captures error message
		try
		{
		 String imgPath = new BaseClass().captureScreen(result.getName());
		 test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	  }

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case skiped is:"+result.getName());
		test.log(Status.INFO,result.getThrowable().getMessage() );
	  }

	 
	
	public void onFinish(ITestContext context) {
		extent.flush(); //all info copied
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try
		{
		 Desktop.getDesktop().browse(extentReport.toURI());
		} catch(Exception e1)
		{
		e1.printStackTrace();
		}
		/*try
		{
		@SuppressWarnings("deprecation")
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		
		//Create email message
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("kavyasilambu@gmail.com","password"));
		email.setSSLOnConnect(true);
		email.setFrom("kavyasilambu@gmail.com");//sender
		email.setSubject("Test Results");
		email.setMsg("Please find attached report...");
		email.addTo("silambu.2700@gmail.com"); //receiver
		email.attach(url, "extent report", "please check report...");
		email.send();//send the email
		} catch(Exception e)
		{
			e.printStackTrace();
		}*/
		}
	  }

 

