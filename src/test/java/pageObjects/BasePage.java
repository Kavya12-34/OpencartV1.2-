package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {  //BasePage class is parent class of all PageObject classes

	WebDriver driver;
 //Constructor
	public BasePage(WebDriver driver)
	{
	 this.driver=driver;
	 PageFactory.initElements(driver,this);
	}
}
