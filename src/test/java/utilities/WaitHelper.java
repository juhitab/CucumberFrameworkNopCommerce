package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	WebDriver driver;
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}
	public void waitforElement(WebElement ele, long timeinsecs) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeinsecs));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitToClickElement(WebElement ele, long timeinsecs) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeinsecs));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
}
