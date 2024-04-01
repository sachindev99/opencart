package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(how = How.XPATH, using="//span[normalize-space()='My Account']")
	WebElement myAccountEl;
	
	@FindBy(how = How.XPATH, using="//a[normalize-space()='Register']")
	WebElement registerEl;
	
	@FindBy(how = How.LINK_TEXT,using = "Login")   // Login link added in step5
	WebElement linkLogin;
	
	public void clickOnTheMyAccount() {
		myAccountEl.click();
	}
	
	public void clickOnTheRegister() {
		registerEl.click();
	}
	
	public void clickLogin()    // added in step5
	{
		linkLogin.click();
	}

}
