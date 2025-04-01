package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	@Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class,groups={"DataDriven","Sanity"})
	public void verify_Login(String email, String pwd,String exp) {
		
		logger.info("***Starting Test TC_003***");
		
		
		try {
		HomePage hm= new HomePage(driver);
		hm.clickMyAccount();
		hm.clickLogin();
		
	
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.ClickLoginButton();
		
		
		MyAccountPage ma = new MyAccountPage(driver);
		boolean targetPage=ma.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				ma.clickLogout();
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
				ma.clickLogout();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
			}
		}
		}catch (Exception e) {
			Assert.fail();
		}
		logger.info("****Finished TC_003 Execution***");

	}
	
	
}
