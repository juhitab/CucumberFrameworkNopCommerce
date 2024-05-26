package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	WebDriver driver;
	WaitHelper waithelper;
	public SearchCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
	}
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement searchEml;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement searchfname;
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement searchlname;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement searchBtn;
	
	@FindBy(how = How.ID, using = "customers-grid")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//table[@id=\"customers-grid\"]/tbody/tr")
	List<WebElement> tableRows;
	@FindBy(how = How.XPATH, using = "//table[@id=\"customers-grid\"]/tbody/tr/td")
	List<WebElement> tableCols;
	
	
	public void enterEmail(String email){
		waithelper.waitforElement(searchEml, 20);
		searchEml.clear();
		searchEml.sendKeys(email);
	}
	public void enterfname(String fname){
		waithelper.waitforElement(searchfname, 20);
		searchfname.clear();
		searchfname.sendKeys(fname);
	}
	public void enterlname(String lname){
		waithelper.waitforElement(searchlname, 20);
		searchlname.clear();
		searchlname.sendKeys(lname);
	}	
	public void clickSearch() {
		searchBtn.click();
	}
	public int getNoofRows() {
		return tableRows.size();
	}
	public int getNoofCols() {
		return tableCols.size();
	}
	public boolean searchCustbyEmail(String email) {
		boolean flag = false;
		for(int i=1; i<=getNoofRows();i++) {
			WebElement emlCol= table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+ i + "]/td[2]")); //col no for email is fixed-2
			if(emlCol.getText().equals(email)) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	public boolean searchCustbyName(String fname, String lname) {
		
		boolean flag = false;
		for(int i=1; i<=getNoofRows();i++) {
			WebElement nameCol= table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+ i + "]/td[3]")); //col no for name is fixed-3
			String displayedName = nameCol.getText();
			String[] nameArr = displayedName.split(" ");
			if(nameArr[0].equalsIgnoreCase(fname) && nameArr[1].equalsIgnoreCase(lname)) {
				flag = true;
				break; // if name exists in table -atleast 1, breaks the loop
			}
		}
		return flag;
	}
	//there can be multiple customers with same name (fname & lname)
	public boolean searchMultiCustbyName(String fname, String lname) { //all names returned in the table should be same
		
		boolean flag = true;
		for(int i=1; i<=getNoofRows();i++) {
			WebElement nameCol= table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+ i + "]/td[3]")); //col no for name is fixed-3
			String displayedName = nameCol.getText();
			String[] nameArr = displayedName.split(" ");
			if(!nameArr[0].equalsIgnoreCase(fname) || !nameArr[1].equalsIgnoreCase(lname)) {
				flag = false;
				break; // if for any record either the firstname doesn't match searched fname or lastname doesn't match searched lname --inconsistency, breaks the loop
			}
		}
		return flag;
	}
}
