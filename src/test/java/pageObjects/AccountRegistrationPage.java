package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver)
	{
	 super(driver); //invokes parent class(Base Page) constructor
	}
	

@FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstname;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtlastname;
@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txtphno;
@FindBy(xpath="//input[@id='input-password']") WebElement txtpw;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtconfirmpw;
@FindBy(xpath="//input[@name='agree']") WebElement checkagree;
@FindBy(xpath="//input[@value='Continue']") WebElement btncontinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmmsg;

public void setfirstname(String fname)
{
	txtfirstname.sendKeys(fname);	
}

public void setlastname(String lname)
{
	txtlastname.sendKeys(lname);	
}

public void setemail(String email)
{
	txtemail.sendKeys(email);	
}

public void setphno(String phno)
{
	txtphno.sendKeys(phno);	
}

public void setpw(String pw)
{
	txtpw.sendKeys(pw);	
}

public void setconfirmpw(String conpw)
{
	txtconfirmpw.sendKeys(conpw);	
}

public void clickagree()
{
	checkagree.click();	
}

public void clickcontinue()
{
	Actions a = new Actions(driver);
	a.moveToElement(btncontinue).click().perform();;	
}

public String getConfirmationMsg()
{
	try
	{
		return(confirmmsg.getText());	
	}catch(Exception e)
	{
	 return(e.getMessage());	
	}
}
}
