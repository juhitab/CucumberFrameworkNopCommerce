package stepDefinitions.NopCom;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	
	public static WebDriver driver; // has to be declared as static - for multiple step definition classes
	public static Logger logger; //static-same reference accessed by all step defi classes
	public static Properties configprop;
	
	public LoginPage lp;
	public AddCustomerPage addCust; //all page object classes referenced here so that any step definition class can access them
	public SearchCustomerPage searchCust;
	
	public static String generateEmail() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}
}
