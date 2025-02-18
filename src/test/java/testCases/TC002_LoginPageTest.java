package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginPageTest extends BaseClass {
  
	@Test(groups={"Sanity","Master"})
  public void logintest() {
		logger.info("Starting TC002_LoginPageTest");
		HomePage hp = new HomePage(driver);
		  hp.clickMyAccount();
		  logger.info("clicked MyAccount");
		  hp.clickLogin();
		  logger.info("clicked Login");
		  
		LoginPage lp = new LoginPage(driver);
		lp.EnterEmail(p.getProperty("email"));
		lp.EnterPass(p.getProperty("password"));
		lp.ClickLogin();
		logger.info("Entered login credentials in login page");
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean MyAcc = mp.IsMyAccountPageDisplayed();
		Assert.assertEquals(MyAcc, true);
		logger.info("My Account Page Displayed");
		
		
		logger.info("Completed TC002_LoginPageTest");
  }
}
