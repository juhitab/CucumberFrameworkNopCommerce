package stepDefinitions.orangeHRM;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*; //package containing all classes for Given When Then etc 

public class OrangeHRMsteps {

	WebDriver driver;
	
	@Given("I launch chrome browser")
	public void i_launch_chrome_browser() {
		driver=new ChromeDriver();	//launch chrome browser
	}
	
	@When("I open Orange HRM homepage")
	public void i_open_orange_hrm_homepage() {
	   driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	@Then("I verify that the logo is present on page")
	public void i_verify_that_the_logo_is_present_on_page() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Boolean status = driver.findElement(By.xpath("//div[@class='orangehrm-login-branding']")).isDisplayed();
		Assert.assertEquals(true, status);
	   
	}
	
	@Then("close the browser")
	public void close_the_browser() {
	   driver.quit();
	}

}
