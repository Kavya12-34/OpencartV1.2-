package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
@FindBy(xpath="//input[@id='input-password']") WebElement txtpass;
@FindBy(xpath="//input[@value='Login']") WebElement btnlogin;

public void EnterEmail(String email)
{
	txtemail.sendKeys(email);
}

public void EnterPass(String pass)
{
	txtpass.sendKeys(pass);
}

public void ClickLogin()
{
	btnlogin.click();
}





}
