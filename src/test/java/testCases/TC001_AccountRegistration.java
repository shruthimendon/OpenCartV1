package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.commons.lang3.RandomStringUtils;
import pageObjects.AccountRegistrationPage;
import pageObjects.BasePage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass{


		@Test(groups={"Regression","Master"})
		public void verify_account_registration()
		{
			
			try
			{
			logger.info("****Test Case started********");
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			
			logger.info("****Starting TC_0001********");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"gmail.com");
			regpage.setTelephone(randomNumber());
			
			String passwordtext=randomAlphaNumeric();
			regpage.setPassword(passwordtext);
			regpage.setConfirmPassword(passwordtext);
			regpage.setPolicy();
			regpage.setContinueButton();
			
		   
		   logger.info("***Validating expected message****");
		   String confmsg= regpage.getConfirmationMsg();
		   if(confmsg.equals("Your Account Has Been Created!!!"))
		   {
			   Assert.assertTrue(true);
		   }
		   else
		   {
			   Assert.assertFalse(false);
		   }
		   
		}catch(Exception e)
			{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
			}
			
		}


}
