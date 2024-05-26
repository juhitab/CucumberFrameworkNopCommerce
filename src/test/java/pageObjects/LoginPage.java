package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	public WebDriver driver; //public why?-to access from multiple packages
	public WebDriverWait wait;
	
	public LoginPage(WebDriver driver){ //public - to be accessed from step definition package for instantiation
		this.driver =  driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Email")
	@CacheLookup
	WebElement email;
	
	@FindBy(id="Password")
	@CacheLookup
	WebElement password;
	
//	name="RememberMe"
	
	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement loginbtn;
	
	@FindBy(linkText = "Logout")
	@CacheLookup
	WebElement logoutLink;
	
	public void setEmail(String emailtxt) {
		email.clear();
		email.sendKeys(emailtxt);
	}
	
	public void setpassword(String pwdtxt) {
		password.clear();
		password.sendKeys(pwdtxt);
	}
	public void clickLogin() {
		loginbtn.click();
	}
	public void clickLogout() {
		waitForLogoutEnabled(); 
		logoutLink.click();
	}
	public void waitForLogoutEnabled() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
		
	}
}
