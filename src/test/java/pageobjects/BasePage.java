package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.BrowserUtils;

public class BasePage {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
//	public static ThreadLocal<BrowserUtils> browserUtils = new ThreadLocal<>();
//	public static WebDriver driver;
//	public static WebDriverWait wait;
//	public static BrowserUtils helper;
	
	public BasePage(WebDriver driver){
		BasePage.driver.set(driver);
		BasePage.wait.set(new WebDriverWait(driver,20));
		BrowserUtils.driver.set(getDriver());
		BrowserUtils.wait.set(getDriverWait());
	}
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public WebDriverWait getDriverWait() {
		return wait.get();
	}

	public BasePage() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void finalize() {
		driver.remove();
		wait.remove();
		driver=null;
		wait=null;
		
	}


}
