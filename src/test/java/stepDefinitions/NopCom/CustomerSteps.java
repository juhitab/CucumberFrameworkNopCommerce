package stepDefinitions.NopCom;

import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.SearchCustomerPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.border.SoftBevelBorder;

import org.junit.Assert;

public class CustomerSteps extends BaseClass{

	static String firstname=""; // global variables to be assigned values from feature/test data file and used in other steps for validation
	static String lastname="";
	
	@When("User clicks on Customers menu")
	public void user_clicks_on_customers_menu() {
		addCust = new AddCustomerPage(driver);
	    addCust.clickCustMenu();
	}

	@When("Clicks on Customers menu item")
	public void clicks_on_customers_menu_item() {
		addCust.clickCustMenuItem();
	}

	@When("Clicks on Add new button")
	public void clicks_on_add_new_button() {
		addCust.clickOnAddnew();
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		
	    Assert.assertEquals(true, driver.getPageSource().contains("Add a new customer"));
	}

	@When("User enters customer info")
	public void user_enters_customer_info() throws InterruptedException {
		logger.info("**** Adding new customer ****");
		String email = generateEmail() + "@gmail.com";
	    addCust.setEmail(email);
	    addCust.setpassword("test12");
	    addCust.setfirstnm("Johny");
	    addCust.setlasstnm("Lever");
	    addCust.selectGenderMale();
	    addCust.setDOB("12-03-2024");
	    addCust.setCompany("Lever");
	    addCust.selectRole("Vendors");
	    addCust.selectVendor("Vendor 1");
	    addCust.setComment("test");
	}

	@When("User clicks on save button")
	public void user_clicks_on_save_button() {
		logger.info("**** Saving customer details ****");
	    addCust.clickOnSave();
	}
	@Then("Validation fails with message {string} if user enters {string} and {string} roles")
	public void user_should_not_enter_roles(String error, String role1, String role2) {
		List<String> checkRoles = new ArrayList<>();
		Collections.addAll(checkRoles,role1,role2); //creating list with “Registered” and “Guests” to check if selected roles list contains this list—should give error message
		if(addCust.getSelectedRoles().containsAll(checkRoles)) {
			Assert.assertEquals(true, driver.getPageSource().contains(error));
			logger.info("**** Error message ****" + error);
		}
	}
	@And("For different roles user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String successmessage) {
		
		Assert.assertEquals(true, driver.getPageSource().contains(successmessage));
		logger.info("**** Success message ****" + successmessage);
	}
	
	//Steps for search customer by email
	@And("Enter customer Email")
	public void enter_customer_email(){
		searchCust = new SearchCustomerPage(driver);
		searchCust.enterEmail("victoria_victoria@nopCommerce.com"); //hardcoding now, but to be sent from excel or feature file
	}
	@And("Click search button")
	public void click_search() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(2000); //needed to wait for the table to reload with searched data
	}
	@Then("User found email in Search table")
	public void email_found_in_Search_table() {
		boolean status = searchCust.searchCustbyEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	@Then("only one record returned for given email")
	public void verify_single_record_per_email() {
		int rowCount = searchCust.getNoofRows();
		Assert.assertEquals(rowCount, 1);
	}
	//Steps for search customer by name
	@When("Enter customer firstname {string} and lastname {string}")
	public void enter_customer_firstname_and_lastname(String first, String last) {
		searchCust = new SearchCustomerPage(driver);//should re-initialise page objects scenario wise (might need to run 1 particular scenario
		firstname = first; //assigned values from feature/test data file and used in other steps for validation
		lastname = last;
		searchCust.enterfname(firstname);
		searchCust.enterlname(lastname);
	}

	@Then("User found name in Search table")
	public void user_found_name_in_search_table() {
	   boolean status = searchCust.searchCustbyName(firstname, lastname); //firstname, lastname are global variables assigned values in previous step from feature/test data file and used in this step for validation
	   Assert.assertEquals(true, status);
	}

	@Then("validate data consistency if mutliple records found with same name")
	public void validate_data_consistency_if_mutliple_records_found_with_same_name() {
		boolean status = searchCust.searchMultiCustbyName(firstname, lastname);
		Assert.assertEquals(true, status);
	}
}
