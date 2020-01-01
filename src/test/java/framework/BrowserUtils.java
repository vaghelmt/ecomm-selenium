package framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testscripts.Test_Ecom;

/**
 * This class contains all the browser interaction methods which helps with
 * common automation tasks like clicking, reading/writing data, moving mouse
 * over an element selecting a value from dropdown and so on It also helps check
 * the state of WebElements. The WebDrvier and WebDriverWait used in this class
 * are initialized in the BasePage {@link Class#BasePage}
 * 
 * @author Mitulsinh Vaghela
 */
public class BrowserUtils {

	/**refers to thread loacal variable for the Webdriver*/
	public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	/**refers to thread loacal variable for the Webdriver Wait*/
	public static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
	/**refers to thread loacal variable for error description used for exception handling*/
	private static final ThreadLocal<String> errorDescription = new ThreadLocal<>();

	/**
	 * This method fetches the WebDriver instance from thread local variable
	 * 
	 * @return WebDriver driver returned from thread local variable
	 * @author Mitulsinh Vaghela
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}

	/**
	 * This method fetches the WebDriverWait instance from thread local variable
	 * 
	 * @return WebDriverWait wait returned from thread local variable
	 * @author Mitulsinh Vaghela
	 */
	public static WebDriverWait getDriverWait() {
		return wait.get();
	}

	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger(BrowserUtils.class);

	/**
	 * This method moves the mouse over a WebElement
	 * 
	 * @param elem WebElement to be acted upon
	 * @throws AutomationException if automation execution is interrupted. the
	 *                             reason for interruption is provided as a string
	 * @author Mitulsinh Vaghela
	 */
	public static void moveMouseOverElement(WebElement elem) throws AutomationException {
		log.info("moveMouseOverElement(WebElement elem) is invoked");
		try {
			scrollIntoView(elem);
			Actions builder = new Actions(getDriver());
			builder.moveToElement(elem).build().perform();
		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());
		} catch (ElementNotVisibleException e) {
			errorDescription.set("WebElement is not visible in viewport");
			throw new AutomationException(errorDescription.get());
		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}
		log.info("moveMouseOverElement(WebElement elem) is completed");
	}

	/**
	 * This method clicks on a WebElement
	 * 
	 * @param elem WebElement to be clicked
	 * @author Mitulsinh Vaghela
	 */
	public static void click(WebElement elem) throws AutomationException {
		log.info("click(WebElement elem) is invoked");
		try {
			// scrollIntoView(driver, elem);
			getDriverWait().until(ExpectedConditions.elementToBeClickable(elem));
			elem.click();

		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());
		} catch (ElementClickInterceptedException e) {
			errorDescription.set("WebElement could not be clicked properly as it may be obscured");
			throw new AutomationException(errorDescription.get());
		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}
		log.info("click(WebElement elem) is completed");

	}

	/**
	 * This method checks if a WebElement is displayed in the view port
	 * 
	 * @param elem WebElement whose state of display is to be verified
	 * @return boolean returns true if WebElement is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public static boolean isDisplayed(WebElement elem) throws AutomationException {
		log.info("isDisplayed(WebElement elem) is invoked and boolean to be returned");
		try {
			// wait.until(ExpectedConditions.visibilityOf(elem));
			return elem.isDisplayed();
		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());

		} catch (ElementNotInteractableException e) {
			errorDescription.set("WebElement can not be interacted as it is obscured temporarily or permanently");
			throw new AutomationException(errorDescription.get());

		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}

	}

	/**
	 * This method takes screenshot of the current state of the screen and stores it
	 * on local driver. The path to the screenshot is returned as a string
	 * 
	 * @param driver         reference to WebDriver
	 * @param screenshotName name of the screenshot
	 * @return String path to the screenshot saved on local drive
	 * @author Mitulsinh Vaghela
	 */
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		log.info("getScreenshot(WebDriver driver, String screenshotName) is invoked");
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		log.info(
				"getScreenshot(WebDriver driver, String screenshotName) is completed and screenshot path to be returned");
		return destination;

	}

	/**
	 * This method configures a select element as a dropdown and then selects the
	 * value based on selenium's select by visible text technique
	 * 
	 * @param elem        WebElement to be configured as a dropdown
	 * @param productSize value to be selected in the dropdown
	 * @author Mitulsinh Vaghela
	 */
	public static void selectValueByName(WebElement elem, String productSize) throws AutomationException {
		log.info("selectValueByName(WebElement elem, String productSize) is invoked");
		try {
			Select dropDown = new Select(elem);
			dropDown.selectByVisibleText(productSize);
		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());

		} catch (ElementNotInteractableException e) {
			errorDescription.set("WebElement can not be interacted as it is obscured temporarily or permanently");
			throw new AutomationException(errorDescription.get());

		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}
		log.info("selectValueByName(WebElement elem, String productSize) is completed");

	}

	/**
	 * This method switches the automation control to frame provided as an argument
	 * 
	 * @param elem frame WebElement
	 * @author Mitulsinh Vaghela
	 */
	public static void switchToFrame(WebElement frameName) throws AutomationException {
		log.info("switchToFrame(WebElement frameName) is invoked");
		getDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));

		// driver.switchTo().frame(frameName);
		try {
			log.info("Waiting for 2 seconds as swithing frame process is slow");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());

		} catch (NoSuchFrameException e) {
			errorDescription.set("Targeted frame not found");
			throw new AutomationException(errorDescription.get());

		} catch (ElementNotInteractableException e) {
			errorDescription.set("WebElement can not be interacted as it is obscured temporarily or permanently");
			throw new AutomationException(errorDescription.get());

		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}
		log.info("switchToFrame(WebElement frameName) is completed");
	}

	/**
	 * This method sets value in a textbox element
	 * 
	 * @param elem textbox WebElement
	 * @author Mitulsinh Vaghela
	 */
	public static void write(WebElement elem, String text) throws AutomationException {
		log.info("write(WebElement elem, String text) is invoked");
		try {
			elem.sendKeys(text);
		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());

		} catch (ElementNotInteractableException e) {
			errorDescription.set("WebElement can not be interacted as it is obscured temporarily or permanently");
			throw new AutomationException(errorDescription.get());

		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}
		log.info("write(WebElement elem, String text) is completed");
	}

	/**
	 * This method configures a select element as a dropdown and then selects the
	 * value based on selenium's select by value technique
	 * 
	 * @param elem        WebElement to be configured as a dropdown
	 * @param productSize value to be selected in the dropdown
	 * @author Mitulsinh Vaghela
	 */
	public static void selectValueByValue(WebElement elem, String value) throws AutomationException {
		log.info("selectValueByValue(WebElement elem, String value) is invoked");
		try {
			Select dropDown = new Select(elem);
			dropDown.selectByValue(value);
		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());

		} catch (ElementNotInteractableException e) {
			errorDescription.set("WebElement can not be interacted as it is obscured temporarily or permanently");
			throw new AutomationException(errorDescription.get());

		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}
		log.info("selectValueByValue(WebElement elem, String value) is completed");

	}

	/**
	 * This method scrolls a particular WebElement into view. It uses selenium's
	 * javascript executor to achieve this.
	 * 
	 * @param elem WebElement to be scolled into view
	 * @author Mitulsinh Vaghela
	 */
	public static void scrollIntoView(WebElement elem) throws AutomationException {
		log.info("scrollIntoView(WebElement elem) is invoked");
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", elem);
		} catch (Exception e) {
			errorDescription.set("Error occured while running javascript executor");
			throw new AutomationException(errorDescription.get());
		}
		log.info("scrollIntoView(WebElement elem) is completed");
	}

	/**
	 * This method switches the automation control to the default frame
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public static void switchToDefaultContent() throws AutomationException {
		log.info("switchToDefaultContent() is invoked");
		getDriver().switchTo().defaultContent();
		try {
			log.info("Waiting for 2 seconds as swithing frame process is slow");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StaleElementReferenceException e) {
			errorDescription.set("WebElement reference is stale");
			throw new AutomationException(errorDescription.get());

		} catch (NoSuchWindowException e) {
			errorDescription.set("Could not switch to default window. Window could have been closed.");
			throw new AutomationException(errorDescription.get());

		} catch (Exception e) {
			throw new AutomationException(e.toString());
		}
		log.info("switchToDefaultContent() is completed");
	}

	@Override
	public void finalize() {
		driver.remove();
		wait.remove();

	}

}
