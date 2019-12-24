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
 * The BaseTest class initializes the test environment It also cleans up
 * resources once test is over.
 */
public class BaseTest {
	/**
	 * variable to load configuration data
	 */
	Properties prop;

	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
//	public RemoteWebDriver driver = null;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;
	public static ExtentReports extent;
	public static WebDriverWait wait;

	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger("ecomm");

	@BeforeSuite
	public void beforeSuiteSetUp() {
		log.info("***************Test Execution Starts********************");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		htmlReporter.config().setDocumentTitle("Automation Execution Report");
		htmlReporter.config().setReportName("Ecommerce Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname", "Mitul's PC");
		extent.setSystemInfo("OS", "WIN10");
		extent.setSystemInfo("SDET", "Mitulsinh Vaghela");
	}

	@AfterSuite
	public void afterSuiteTearDown() {
		quitWebDriver();
		log.info("***************Test Execution Ends***********************");
		extent.flush();
	}

	@BeforeTest
	@Parameters({ "browser", "grid", "implicitWait", "explicitWait"})
	public void beforeTestRun(String browser, String grid, int implicitWait, int explicitWait) {
		log.info("***************Test Case Execution Starts********************" + Thread.currentThread().getId());
		initWebDriver(browser, grid);
		configureDriver(implicitWait,implicitWait);

	}

	@AfterTest
	public void afterTestRun() {
		// quitWebDriver();
		closeWebDriver();
		log.info("***************Test Case Execution Ends********************" + Thread.currentThread().getId());
	}

	@BeforeMethod
	public void beforeMethod() {
		// initWebDriver(browser, grid);
		// System.out.println("Before method: " + Thread.currentThread().getId());
	}

	@AfterMethod
	public void AfterMethod(ITestResult result) {
		// closeWebDriver();

		try {
			String screenshotPath = BrowserUtils.getScreenshot(getDriver(), result.getName());

			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.FAIL, "TEST CASE FAILED: " + result.getName()); // name
				test.log(Status.FAIL, "TEST CASE FAILED: " + result.getThrowable()); // error description
				test.addScreenCaptureFromPath(screenshotPath);
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(Status.SKIP, "TEST CASE SKIPPED: " + result.getName()); // name
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS, "TEST CASE PASSED: " + result.getName()); // name
				test.addScreenCaptureFromPath(screenshotPath);
			}

			System.out.println("After method: " + Thread.currentThread().getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	private void closeWebDriver() {
		getDriver().quit();
	}

	private void quitWebDriver() {
		driver.remove();

	}

	private void initWebDriver(String browser, String grid) {
		/**
		 * Initialize webdriver as per configuration file
		 */
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void configureDriver(int implicitWait, int explicitWait) {
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		wait = new WebDriverWait(getDriver(),explicitWait);
	}

}
