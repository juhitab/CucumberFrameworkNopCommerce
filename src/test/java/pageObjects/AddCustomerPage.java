package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.WaitHelper;


public class AddCustomerPage {
	WebDriver driver;
	WaitHelper waithelp;
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waithelp = new WaitHelper(driver);
	}
	
	//locating elements using By
	By customerMenu = By.xpath("//a/p[contains(text(),'Sales')]/ancestor::li/following-sibling::li/a/p[contains(text(),'Customers')]");
	By customerMenuitem = By.xpath("//a/p[contains(text(),'Sales')]/ancestor::li/following-sibling::li/ul//a/p[text()=' Customers']");
	By Addnew = By.xpath("//a[@href='/Admin/Customer/Create']");
	By emailtxt  = By.id("Email");
	By passwordtxt  = By.id("Password");
	By firstname  = By.id("FirstName");
	By lastname  = By.id("LastName");
	By genderM  = By.id("Gender_Male");
	By genderF  = By.id("Gender_Female");
	By dob_ele  = By.name("DateOfBirth");
	By company  = By.id("Company");
	By taxexempt  = By.name("IsTaxExempt");
	
	By custRoles  = By.xpath("//div[contains(@title,'customer roles')]/ancestor::div/following-sibling::div//span[@class='selection']");
	By custAdmin = By.xpath("//li[contains(text(),'Administrators')]");
	By custForum = By.xpath("//li[contains(text(),'Forum Moderators')]");
	By custRegis = By.xpath("//li[contains(text(),'Registered')]");
	By custGuest = By.xpath("//li[contains(text(),'Guests')]");
	By custVendor = By.xpath("//li[contains(text(),'Vendors')]");
	
	By selectedRoles = By.xpath("//span[@role='combobox']/ul/li[contains(@class,'choice')]");
	By managerofvendor  = By.id("VendorId");
	By comment  = By.id("AdminComment");
	By savebtn = By.name("save");
	
	//Action methods
	public void clickCustMenu() {
		driver.findElement(customerMenu).click();
	}
	public void clickCustMenuItem() {
		WebElement cmenuitem = driver.findElement(customerMenuitem);
		waithelp.waitToClickElement(cmenuitem, 10);
		cmenuitem.click();
	}
	public void clickOnAddnew() {
		driver.findElement(Addnew).click();
	}
	public void setEmail(String email) {
		driver.findElement(emailtxt).sendKeys(email);
	}
	public void setpassword(String pwd) {
		driver.findElement(passwordtxt).sendKeys(pwd);
	}
	public void setfirstnm(String fname) {
		driver.findElement(firstname).sendKeys(fname);
	}
	public void setlasstnm(String lname) {
		driver.findElement(lastname).sendKeys(lname);
	}
	public void selectGenderMale() {
		driver.findElement(genderM).click();
	}
	public void selectGenderFemale() {
		driver.findElement(genderF).click();
	}
	public void setDOB(String dobtxt) {
		driver.findElement(dob_ele).sendKeys(dobtxt);
	}
	public void setCompany(String companyname) {
		driver.findElement(company).sendKeys(companyname);
	}
	public void selectRole(String role) throws InterruptedException {
		
		driver.findElement(custRoles).click();
		Thread.sleep(3000);
		//depending on role passed from step defi class 
		if(role.equals("Administrators"))
			driver.findElement(custAdmin).click();
		else if(role.equals("Forum Moderators"))
			driver.findElement(custForum).click();
		else if(role.equals("Guests"))
			driver.findElement(custGuest).click();
		else if(role.equals("Vendors"))
			driver.findElement(custVendor).click();
		else	//"Registered" role is selected already by default
			driver.findElement(custRegis).click();
		
	}
	public void selectVendor(String vendor) {
		Select vendordrp = new Select(driver.findElement(managerofvendor));
		vendordrp.selectByVisibleText(vendor);
	}
	public void setComment(String commenttxt) {
		driver.findElement(comment).sendKeys(commenttxt);
	}
	//retrieving all the selected roles as list- for validation 
	public List<String> getSelectedRoles() {
		List<WebElement> selectedlist = driver.findElements(selectedRoles);
//		String roles = "";
		List<String> roles = new ArrayList<String>();
		for(WebElement item : selectedlist) {
//			roles += item.getText() + "#";
			roles.add(item.getText());
		}
		return roles;
	}
	public void clickOnSave() {
		driver.findElement(savebtn).click();
	}
	
}
