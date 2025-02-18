package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
  @Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
  public void ValidateLogin(String email,String pass,String exp) {
	  
	  logger.info("Starting TC003_LoginDDT");
		
	  //Home Page
	  try
	  {
	  HomePage hp = new HomePage(driver);
	  hp.clickMyAccount();
	  logger.info("clicked MyAccount");
	  hp.clickLogin();
	  logger.info("clicked LoginLink");
		
	  //Login page
		LoginPage lp = new LoginPage(driver);
		lp.EnterEmail(email);
		lp.EnterPass(pass);
		lp.ClickLogin();
		logger.info("Entered login credentials in login page");
		
		//MyAccount page
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetPage = mp.IsMyAccountPageDisplayed();
		
		/*Data is valid - Login success - test pass - logout
		 * Data is valid - Login fail - test fail
		 * 
		 * Data is invalid - Login success - test fail - logout
		 * Data is invalid - Login fail - test pass
		 */
		
		if(exp.equalsIgnoreCase("valid"))
		{
		 if(targetPage==true)
		 {
			 mp.clicklogout();
			 Assert.assertTrue(true);
					 }
		 else
		 {
		  Assert.assertTrue(false);	 
		 }
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
		 if(targetPage==true)
		 {
			 mp.clicklogout();
			 Assert.assertTrue(false);
					 }
		 else
		 {
		  Assert.assertTrue(true);	 
		 }
		}
	  }
	  catch(Exception e)
	  {
		Assert.fail();  
	  }
		
			
		logger.info("Completed TC003_LoginDDT");
  }
}
