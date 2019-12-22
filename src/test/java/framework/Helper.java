package framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Helper {
	
	public static void moveMouseOverElement(WebDriver driver, WebElement elem) {
		Actions builder = new Actions(driver);
		builder.moveToElement(elem).build().perform();
	}

	public static void click(WebDriver driver, WebElement elem) {
		elem.click();
		
	}

	public static boolean isDisplayed(WebDriver driver, WebElement elem) {
		return elem.isDisplayed();
	}
	
	public static String  getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
		
	}

	public static void selectValueByName(WebDriver driver, WebElement elem,String productSize) {
		Select dropDown = new Select(elem);
		dropDown.selectByValue(productSize);
		
	}


}
