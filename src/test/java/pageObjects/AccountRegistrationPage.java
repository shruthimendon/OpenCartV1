package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname")
	WebElement firstname;
	
	@FindBy(xpath="//input[@id='input-lastname")
	WebElement lastname;
	
	@FindBy(xpath="//input[@id='input-email")
	WebElement emailid;
	

	@FindBy(xpath="//input[@id='input-telephone")
	WebElement telephonenumber;
	
	@FindBy(xpath="//input[@id='input-password")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm")
	WebElement txtconfirmpassword;
	

	@FindBy(xpath="//input[@name='agree")
	WebElement checkpolicy;
	

	@FindBy(xpath="//input[@value='Continue")
	WebElement Continuebutton;
	
	@FindBy(xpath="//h1[normalise-space()='Your Account Has Been Created")
	WebElement msgconfirmation;
	
	public void setFirstName(String fname)
	{
		firstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		lastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		emailid.sendKeys(email);
	}
	
	public void setTelephone(String telephone)
	{
		telephonenumber.sendKeys(telephone);
	}
	

	public void setPassword(String password)
	{
		txtpassword.sendKeys(password);
	}

	public void setConfirmPassword(String confirmpassword)
	{
		txtconfirmpassword.sendKeys(confirmpassword);
	}

	public void setPolicy()
	{
		checkpolicy.click();
	}

	public void setContinueButton()
	{
		Continuebutton.click();
	}
	
	public String getConfirmationMsg()
	{
		try
		{
			return(msgconfirmation.getText());
		}catch(Exception e)
		{
			return (e.getMessage());
		}
	}
}
