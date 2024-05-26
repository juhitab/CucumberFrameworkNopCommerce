package stepDefinitions.NopCom;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import pageObjects.LoginPage;

public class LoginSteps extends BaseClass{ //common variables(like driver,logger etc) and methods extended from BaseClass
	
	@Before	//Execute method before each scenario.
	public void setup() throws IOException {
		configprop = new Properties();
		FileInputStream configfile =  new FileInputStream("config.properties");
		configprop.load(configfile);
		
		logger = Logger.getLogger("NopCommerceLog");
		PropertyConfigurator.configure("log4j.properties"); //path of the log4j.properties file
	}
	
	@Given("User launches browser")	//this quoted text inside annotation (@Given("User launches browser")) is the only connection/mapping between stepdefi file and feature file
	public void user_launches_browser() {
		logger.info("**** Launching browser ****");
		String brow = configprop.getProperty("browser");
		if(brow.equalsIgnoreCase("chrome"))
			//instantiation
			driver = new ChromeDriver(); //launches browser- declared as static in BaseClass
		else if(brow.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(brow.equalsIgnoreCase("ie"))
			driver = new InternetExplorerDriver();
		
		lp =  new LoginPage(driver); //driver object passed as parameter to LoginPage constructor to lookup page elements
	}

	@When("User opens the site url")
	public void user_opens_the_site_url() {
		logger.info("**** Opening URL ****");
	    driver.get(configprop.getProperty("url"));
	    driver.manage().window().maximize();
	}

	@When("User enters the Login email {string} and password {string}")
	public void user_enters_the_login_email_and_password(String email, String password) {
		logger.info("**** Entering credentials ****");
	    lp.setEmail(email);
	    lp.setpassword(password);
	}

	@When("User clicks log in button")
	public void user_clicks_log_in_button() {
	   lp.clickLogin();
	   driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {

		if(driver.getPageSource().contains("unsuccessful")) {
			logger.error("**** Login failed ****");
			close_browser();	//using close_browser() method/step definition of same class
			Assert.assertTrue("Invalid credentials",false);
		}else {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			logger.info("**** User Logged in ****");
		}
		
	}
	@And("Page should contain {string}")
	public void page_should_contain(String expectedtext) {
		Assert.assertEquals(true, driver.getPageSource().contains(expectedtext));
	}

	@When("User clickslogout link")
	public void user_clickslogout_link() {
	    lp.clickLogout();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		logger.info("**** User Logged out ****");
	}
	
//	Then Page Title should be "Your store. Login" -- same @Then("Page Title should be {string}") should be called with different title parameter--already implemented
	
	@Then("Close browser")
	public void close_browser(){
		logger.info("**** Closing browser ****");
	    driver.quit();
	    
		/*driver.close(); it closes driver but throws java.net.SocketException after closing browser - Connection reset. driver.quit() doesn't throw
		this exception cannot be caught since it doesn't stop execution, it closes the browser successfully, but socket is still open
		This SocketException occurs on the server-side when the client closed the socket connection before the response could be returned over the socket. Example, by quitting the browser before the response was retrieved.
		*/
	}
}
