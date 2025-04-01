package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage	 	 	
{
  WebDriver driver;

  
  public HomePage(WebDriver driver)
  {
	  super(driver);
  }
  
  @FindBy(xpath="//a[@title='My Account']/span[1]")
  WebElement linkMyAccount;
  
  @FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']/li/a")
  WebElement linkRegister;
  
  @FindBy(linkText = "Login")
  WebElement loginlink;
  
  public void clickMyAccount()
  {
	  linkMyAccount.click();
	  
  }
  
  public void clickRegister()
  {
	  linkRegister.click();
  }
  
  public void clickLogin()
  {
	  loginlink.click();
  }
}
