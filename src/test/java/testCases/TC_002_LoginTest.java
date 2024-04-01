package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	@Test(groups= {"sanity","master"})
	public void verifyLogin() {
		
		logger.info("starting TC_002_LoginTest");
		logger.debug("application logs...");
		
		//Home page
		HomePage hp = new HomePage(driver);
		hp.clickOnTheMyAccount();
		logger.info("Clicked On MyAccount Link");
		hp.clickLogin(); //login link under MyAccount
		logger.info("Clicked On login link under myaccount");
		
		//Login page
		
		LoginPage lp = new LoginPage(driver);
		logger.info("Providing email and password");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin(); //Login button
		
		
		//My Account Page
		logger.info("verifying ");
		MyAccountPage macc =new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		if(targetPage==true) {
			logger.info("Login test passed...");
			Assert.assertTrue(true);
		}
		
		else {
			logger.error("login test failed");
			Assert.fail();
		}
		
		logger.info("Finished TC_002_LoginTest");
		
	}

}
