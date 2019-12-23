package framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger("ecomm");

	public static void moveMouseOverElement(WebDriver driver, WebDriverWait wait, WebElement elem) {
		Actions builder = new Actions(driver);
		builder.moveToElement(elem).build().perform();
	}

	public static void click(WebDriver driver, WebDriverWait wait, WebElement elem) {
		try {
			//scrollIntoView(driver, elem);
			wait.until(ExpectedConditions.elementToBeClickable(elem));
			elem.click();

		} catch (Exception e) {
			throw new AutomationException(e.getMessage());
		}

	}

	public static boolean isDisplayed(WebDriver driver, WebDriverWait wait, WebElement elem) {
		return elem.isDisplayed();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}

	public static void selectValueByName(WebDriver driver, WebDriverWait wait, WebElement elem, String productSize) {
		Select dropDown = new Select(elem);
		dropDown.selectByVisibleText(productSize);

	}

	public static void switchToFrame(WebDriver driver, WebDriverWait wait, WebElement frameName) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		// driver.switchTo().frame(frameName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Swithed to Quick View frame successfully");
	}

	public static void write(WebDriver driver, WebDriverWait wait, WebElement elem, String text) {
		elem.sendKeys(text);
	}

	public static void selectValueByValue(WebDriver driver, WebDriverWait wait, WebElement elem,
			String value) {
		Select dropDown = new Select(elem);
		dropDown.selectByValue(value);
		
	}
	
	public static void scrollIntoView(WebDriver driver, WebElement elem) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
	}

}
