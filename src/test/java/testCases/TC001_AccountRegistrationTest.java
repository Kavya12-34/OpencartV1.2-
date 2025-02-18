package testCases;

import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import org.testng.Assert;

public class TC001_AccountRegistrationTest extends BaseClass {
	  
    @Test(groups={"Regression","Master"})
  public void test_accountregister() throws InterruptedException {
      logger.info("Starting TC001_AccountRegistrationTest");
	       
      HomePage hp = new HomePage(driver);
	  hp.clickMyAccount();
	  logger.info("clicked MyAccount");
	  hp.clickRegister();
	  logger.info("clicked Register");
	  
	  AccountRegistrationPage ap = new AccountRegistrationPage(driver);
	  logger.info("Entering customer details");
	  ap.setfirstname(randomstring().toUpperCase());
	  ap.setlastname(randomstring().toUpperCase());
	  ap.setemail(randomstring()+"@gmail.com");
	  ap.setphno(randomnumber());
	  String pw = alphanumeric();
	  ap.setpw(pw);
	  ap.setconfirmpw(pw);
	  ap.clickagree();
	  ap.clickcontinue();
	  
	  logger.info("validating test msg");
	  String msg = ap.getConfirmationMsg();
	  if(msg.equals("Your Account Has Been Created!"))
	  {
	  Assert.assertTrue(true);
	  logger.info("Test Case passed");
	  }
	  else
	  {
		  logger.error("Test case failed");
		  Assert.assertTrue(false);
		    
	  }
	  Thread.sleep(2000);
	 
	   
	   
	  
	  logger.info("Finished TC001_AccountRegistrationTest");
  }
  
 
  

}
