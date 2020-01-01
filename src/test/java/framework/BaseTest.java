package framework;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * The BaseTest class acts as a base for all the tests. As such, it initializes
 * the overall test environment i.e. instantiates drivers as per configurations,
 * initializes the logging and reporting framework and basically taking care of
 * tasks that are common to all the tests. It also cleans up resources once test
 * is over.
 * 
 * @author Mitulsinh Vaghela
 */
public class BaseTest {
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public static ThreadLocal<String> errorDescription = new ThreadLocal<>();
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;
	public static ExtentReports extent;
	public static WebDriverWait wait;

	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger(BaseTest.class);

	/**
	 * This TestNG "Before suite" method is a batch execution level method. Before
	 * every test execution starts, it sets up batch level environment variables
	 * like logger, reporting variables and so on.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	@BeforeSuite
	public void beforeSuiteSetUp() {
		log.info("***************Test Execution Starts********************");
		log.info("Extent Report HTMLReporter configuration starts");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		htmlReporter.config().setDocumentTitle("Automation Execution Report");
		htmlReporter.config().setReportName("Ecommerce Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		log.info("HTML Reporter configuration ends");
		log.info("Extent report initialization and system information setting starts");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname", "Mitul's PC");
		extent.setSystemInfo("OS", "WIN10");
		extent.setSystemInfo("SDET", "Mitulsinh Vaghela");
		log.info("Extent report initialization and system information setting ends");
	}

	/**
	 * This TestNG "After suite" method is a batch execution level method. After
	 * every test execution starts, it tears down the environment variables and
	 * create the result report.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	@AfterSuite
	public void afterSuiteTearDown() {
		quitWebDriver();
		log.info("***************Test Execution Ends***********************");
		log.info("Extent report preparation starts");
		extent.flush();
		log.info("Extent report preparation ends");
	}

	/**
	 * This TestNG "Before test" method is used instantiate and configure the
	 * WebDriver based on preference set in the testng.xml file
	 * 
	 * @author Mitulsinh Vaghela
	 */
	@BeforeTest
	@Parameters({ "browser", "grid", "implicitWait", "explicitWait" })
	public void beforeTestRun(String browser, String grid, int implicitWait, int explicitWait) {
		log.info("***************Test Case Execution Starts********************" + Thread.currentThread().getId());
		initWebDriver(browser, grid);
		configureDriver(implicitWait, implicitWait);

	}

	/**
	 * This TestNG "after test" method is used to close all WebDriver once the test
	 * is completed
	 * 
	 * @author Mitulsinh Vaghela
	 */
	@AfterTest
	public void afterTestRun() {
		closeWebDriver();
		log.info("***************Test Case Execution Ends********************" + Thread.currentThread().getId());
	}

	//Not required for now. May be in future
	//Comment added 1st JAN 2020 by Mitulsinh Vaghela
//	@BeforeMethod
//	public void beforeMethod() {
//		// initWebDriver(browser, grid);
//		// System.out.println("Before method: " + Thread.currentThread().getId());
//	}

	/**
	 * This TestNG "after method" method is used to read the TestNG ITestResult
	 * object after each method run, determines if the test was pass or failed and
	 * accordingly makes an entry in the Extent Report along with a screenshot
	 * 
	 * @param result ITestResult provided by TestNG framework
	 * @author Mitulsinh Vaghela
	 */
	@AfterMethod
	public void AfterMethod(ITestResult result) {
		// closeWebDriver(); //for method level parallel executed. Commenting for current
		//configuration. Comment added on 1st Jan 2020 by Mitulsinh Vaghela

		try {
			String screenshotPath = BrowserUtils.getScreenshot(getDriver(), result.getName());

			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.FAIL, "TEST CASE FAILED: " + result.getMethod()); // name
				test.log(Status.FAIL, "TEST CASE FAILED: " + result.getThrowable()); // error description
				test.addScreenCaptureFromPath(screenshotPath);
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(Status.SKIP, "TEST CASE SKIPPED: " + result.getMethod()); // name
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS, "TEST CASE PASSED: " + result.getMethod()); // name
				test.addScreenCaptureFromPath(screenshotPath);
			}

			System.out.println("After method: " + Thread.currentThread().getId());
		} catch (IOException e) {
			errorDescription.set("I/O exception was thrown");
		} catch (AutomationException e) {
			throw new AutomationException(e.toString());
		}
	}

	/**
	 * This method fetches the WebDriver instance from thread local variable
	 * 
	 * @return WebDriver driver returned from thread local variable
	 * @author Mitulsinh Vaghela
	 */
	public WebDriver getDriver() {
		return driver.get();
	}

	/**
	 * This method quits all the active instances of WebDrivers running in a
	 * particular thread
	 * 
	 * @author Mitulsinh Vaghela
	 */
	private void closeWebDriver() {
		getDriver().quit();
	}

	/**
	 * This method removes the instance of WebDriver present in a particular thread
	 * local variable
	 * 
	 * @author Mitulsinh Vaghela
	 */
	private void quitWebDriver() {
		driver.remove();

	}

	/**
	 * This method instantiates WebDrivers based on browser preferences set in the
	 * testng.xml
	 * 
	 * @author Mitulsinh Vaghela
	 */
	private void initWebDriver(String browser, String grid) {
		/**
		 * Initialize webdriver as per configuration file
		 */
		log.info("initWebDriver(String browser, String grid) invoked");
		try {
			switch (browser) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				driver.set(new RemoteWebDriver(new URL(grid + "/wd/hub"), chromeOptions));
				break;

			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driver.set(new RemoteWebDriver(new URL(grid + "/wd/hub"), firefoxOptions));
				break;

			case "IE":
				InternetExplorerOptions ieOption = new InternetExplorerOptions();
				driver.set(new RemoteWebDriver(new URL(grid + "/wd/hub"), ieOption));
			default:
				break;
			}
			log.info("initWebDriver(String browser, String grid) is completed");
		} catch (MalformedURLException e) {
			errorDescription.set("MalformedULR exception was thrown");
		} catch (AutomationException e) {
			throw new AutomationException(e.toString());

		}

	}

	/**
	 * This method takes care of driver configurations i.e. setting up driver size
	 * and resolution, configuring implicit and explicit waits and so on.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	private void configureDriver(int implicitWait, int explicitWait) {
		try {
			log.info("configureDriver(int implicitWait, int explicitWait) is invoked");
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			wait = new WebDriverWait(getDriver(), explicitWait);
			log.info("configureDriver(int implicitWait, int explicitWait) is completed");
		} catch (AutomationException e) {
			throw new AutomationException(e.toString());

		}
	}

}
