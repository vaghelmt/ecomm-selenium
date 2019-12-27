package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.BrowserUtils;

/**
 * This class as base class for all the page objects. It declares and sets the threadlocal
 * WebDriver and WebDrvierWait references which are then used by all the page objects
 * @author Mitulsinh Vaghela
 */
public class BasePage {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
	private static final Logger log = Logger.getLogger(BasePage.class);
//	public static ThreadLocal<BrowserUtils> browserUtils = new ThreadLocal<>();
//	public static WebDriver driver;
//	public static WebDriverWait wait;
//	public static BrowserUtils helper;
	
	/**
	 * This constructor is used by the Home Page object which is the first page object
	 * instantiated in this framework. As such, this constructor initializes the 
	 * WebDriver and WebDriverWait instances using the driver instance received 
	 * from the test class. These instances are further used by all the page objects
	 * and the utility classes like BrowserUtils.
	 * @author Mitulsinh Vaghela
	 */
	public BasePage(WebDriver driver){
		BasePage.driver.set(driver);
		BasePage.wait.set(new WebDriverWait(driver,20));
		BrowserUtils.driver.set(getDriver());
		BrowserUtils.wait.set(getDriverWait());
	}
	
	/**
	 * This method fetches the WebDriver instance from thread local variable
	 * @return WebDriver driver returned from thread local variable
	 * @author Mitulsinh Vaghela
	 */
	public WebDriver getDriver() {
		return driver.get();
	}
	
	/**
	 * This method fetches the WebDriverWait instance from thread local variable
	 * @return WebDriverWait wait returned from thread local variable
	 * @author Mitulsinh Vaghela
	 */
	public WebDriverWait getDriverWait() {
		return wait.get();
	}

	
	/**
	 * This constructor is used by page objects other than the home page
	 * @author Mitulsinh Vaghela
	 */
	public BasePage() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Destructor
	 * @author Mitulsinh Vaghela
	 */
	@Override
	public void finalize() {
		driver.remove();
		wait.remove();
		driver=null;
		wait=null;
		
	}


}
