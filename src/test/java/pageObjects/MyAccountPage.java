package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement txtMyaccount;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnklogout;
	
	public boolean IsMyAccountPageDisplayed()
	{
		try
		{
		boolean display = txtMyaccount.isDisplayed();
		return display;
				}catch(Exception e) {
		return false;
				}
		
	}
	
	public void clicklogout()
	{
		lnklogout.click();	
	}

}
