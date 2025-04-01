package testCases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_003_LoginTest2 extends BaseClass {
	
	@Test(groups="Master")
	public void verify_Login() {
		
		logger.info("***Starting Test TC_002***");
		
		try {
		
		
		HomePage hm= new HomePage(driver);
		hm.clickMyAccount();
		hm.clickLogin();
		
	
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.ClickLoginButton();
		
		
		MyAccountPage ma = new MyAccountPage(driver);
		boolean targetPage=ma.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
		
		}
		catch (Exception e) {
			
			Assert.fail();
			// TODO: handle exception
		}
		logger.info("***Finishing Test TC_002***");
		
	}

}
