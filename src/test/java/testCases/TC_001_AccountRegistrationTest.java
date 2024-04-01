package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"regression","sanity"})
	public void verify_account_registration() {
		logger.info("***** statring TC_001_AccountRegristerationTest ****");
		logger.debug("application logs...");
		try {
		
			HomePage hp = new HomePage(driver);
			hp.clickOnTheMyAccount();
			logger.info("****Clicked On MyAccount Link******");
			hp.clickOnTheRegister();
			logger.info("****Clicked On Registeration Link******");
			
			logger.info("****Entering customer info******");
			AccountRegistrationPage reg = new AccountRegistrationPage(driver);
			reg.setFirstName(randomString().toUpperCase()); 
			reg.setLastName(randomString().toUpperCase());
			reg.setEmail(randomString()+"@gmail.com");
			String password = randomAlphaNUmeric();
			reg.setPassword(password);
			reg.setPrivacyPolicy();
			reg.clickContinue();
			logger.info("*****Validating expected message*******");
			System.out.println(password);
			String confmsg = reg.getConfirmationMsg();
			AssertJUnit.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		
		catch(Exception e) {
			logger.error("Test failed....");
		}
		logger.info("****Finished TC_001******");
	}

}
