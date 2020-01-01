package testscripts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import framework.AutomationException;
import framework.BaseTest;
import pageobjects.CheckoutPage;
import pageobjects.HomePage;
import pageobjects.QuickView;

/**
 * This is an automation test case which in essence automates adding a specific
 * product into shopping cart, register new user and finally confirm and place
 * the order The exact steps are as mentioned below - STEP 1: Go to
 * http://automationpractice.com STEP 2: Go to 'Women' and select 'Summer
 * Dresses' STEP 3: From the available products Grid View, mouse over 'Printed
 * Chiffon Dress' and click 'Quick view' STEP 4: Select 'M' size and click on
 * 'Add to Cart' STEP 5: Click on 'Continue shopping' STEP 6: Go to 'Cart' and
 * click 'Check Out' STEP 7: Click 'Proceed to checkout' STEP 8: Enter an email
 * and click on 'Create an Account' STEP 9: Fill in mandatory fields and click
 * 'Register' STEP 10: Click 'Proceed to checkout' on Address tab STEP 11: Agree
 * to 'Terms of service' and Click 'Proceed to checkout' on Shipping tab STEP
 * 12: Confirm the correct order on 'Payment' tab
 * 
 * @author Mitulsinh Vaghela
 */
@Test(groups = "buyProduct")
public class Test_Ecom extends BaseTest {

	private HomePage poHomePage;
	private QuickView poQuickView;
	private CheckoutPage poCheckoutPage;
	private final HashMap<String, String> data;

	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger(Test_Ecom.class);

	@DataProvider(name = "TD_Ecom")
	public static Iterator<Object[]> TestData_Test_Ecom() {
		log.info("Test data provider for Test_Ecom script invoked");
		List<HashMap<String, String>> listOfMap = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		//this commented code is for the demo. This is to show test driver execution
		//is possible with this framework.
		//Here we are hard coding the data. But with this same approach, we can read the
		//data from external data source and prepare the hashmap
		//comment added on 1st JAN 2020 by Mitulsinh Vaghela
//        HashMap<String,String> map2 = new HashMap<String,String>();
		map1.put("mainMenu", "Women");
		map1.put("submenu", "Summer Dresses");
		map1.put("searchResult", "Printed Chiffon Dress");
		map1.put("size", "M");
		map1.put("firstName", "James");
		map1.put("email",
				"amigo" + ((int) Math.ceil(Math.random() * 100)) + ((int) Math.ceil(Math.random() * 100)) + "@xyz.com");
		log.info(map1.get("email"));
		map1.put("lastName", "Bond");
		map1.put("password", "test@1234");
		map1.put("birthDay", "1");
		map1.put("birthMonth", "1");
		map1.put("birthYear", "2000");
		map1.put("addressLine1", "105 Dundas Street");
		map1.put("city", "Toronto");
		map1.put("state", "Florida");
		map1.put("country", "United States");
		map1.put("postalCode", "45689");
		map1.put("phoneNumber", "7895671879");
		map1.put("addressAlias", "Fav Address");
		//this commented code is for the demo. This is to show test driver execution
		//is possible with this framework.
		//Here we are hard coding the data. But with this same approach, we can read the
		//data from external data source and prepare the hashmap
		//comment added on 1st JAN 2020 by Mitulsinh Vaghela
		// map2.put("firstName", "Mahesh");
//        map2.put("email", "amigo17@xyz.com");
		listOfMap.add(map1);
//        listOfMap.add(map2);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (HashMap<String, String> map : listOfMap) {
			dp.add(new Object[] { map });
		}
		log.info("Test data provider for Test_Ecom script is ready and about to be returned");
		return dp.iterator();
	}

	@Factory(dataProvider = "TD_Ecom")
	public Test_Ecom(HashMap<String, String> data) {
		log.info("Factory method for Test_Ecom invoked and data about to be initialized");
		this.data = data;
	}

	@Parameters({ "URL" })
	@Test
	public void verifyLaunchEcomApp(String url) {
		log.info("verifyLaunchEcomApp(String url) invoked");
		try {
			poHomePage = new HomePage(getDriver());
			test = extent.createTest("launchEcommApp", driver.get().getCapabilities().getBrowserName());
			poHomePage.launchApp(url);
			Assert.assertTrue(poHomePage.isLogoDisplayed());
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getErrorMessage());
			test.log(Status.FAIL, "Automation Execution was interrupted: " + e.getErrorMessage());
			log.fatal("Automation Execution was interrupted: " + e.getErrorMessage());
		}
		log.info("verifyLaunchEcomApp(String url) is completed");
	}

	@Test(dependsOnMethods = { "verifyLaunchEcomApp" })
	public void verifyNavigationToACategoryUsingMenu() {
		log.info("verifyNavigationToACategoryUsingMenu() is invoked");
		try {
			test = extent.createTest("navigateToSummerDressesUsingMenu",
					driver.get().getCapabilities().getBrowserName());
			poHomePage.hoverOverMenu(data.get("mainMenu"));
			poHomePage.clickOnSubMenu(data.get("submenu"));
			Assert.assertTrue(poHomePage.isCategoryResultsDisplayed(data.get("submenu")));
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getErrorMessage());
			test.log(Status.FAIL, "Automation Execution was interrupted: " + e.getErrorMessage());
			log.fatal("Automation Execution was interrupted: " + e.getErrorMessage());
		}
		log.info("verifyNavigationToACategoryUsingMenu() is completed");
	}

	@Test(dependsOnMethods = { "verifyNavigationToACategoryUsingMenu" })
	public void verifyMouseOverParticularDressShowsQuickView() {
		log.info("verifyMouseOverParticularDressShowsQuickView() is invoked");
		try {
			test = extent.createTest("verifyMouseOverParticularDressShowsQuickView",
					driver.get().getCapabilities().getBrowserName());
			poHomePage.hoverOverSearchResult(data.get("searchResult"));
			Assert.assertTrue(poHomePage.isQuickViewDisplayed());
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getErrorMessage());
			test.log(Status.FAIL, "Automation Execution was interrupted: " + e.getErrorMessage());
			log.fatal("Automation Execution was interrupted: " + e.getErrorMessage());
		}
		log.info("verifyMouseOverParticularDressShowsQuickView() is completed");
	}

	@Test(dependsOnMethods = { "verifyMouseOverParticularDressShowsQuickView" })
	public void verifyProductConfigurationAndAddingToCart() {
		log.info("verifyProductConfigurationAndAddingToCart() is invoked");
		try {
			test = extent.createTest("verifyProductConfigurationAndAddingToCart",
					driver.get().getCapabilities().getBrowserName());
			poQuickView = poHomePage.clickOnQuickView();
			poQuickView.selectProductSize(data.get("size"));
			poQuickView.addToCart();
			Assert.assertTrue(poHomePage.isAddToCartSuccessful());
			poHomePage.continueShopping();
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getErrorMessage());
			test.log(Status.FAIL, "Automation Execution was interrupted: " + e.getErrorMessage());
			log.fatal("Automation Execution was interrupted: " + e.getErrorMessage());
		}
		log.info("verifyProductConfigurationAndAddingToCart() is completed");
	}

	@Test(dependsOnMethods = { "verifyProductConfigurationAndAddingToCart" })
	public void verifyNavigationToCheckoutPage() {
		log.info("verifyNavigationToCheckoutPage() is invoked");
		try {
			test = extent.createTest("verifyNavigationToCheckoutPage", driver.get().getCapabilities().getBrowserName());
			poCheckoutPage = poHomePage.goToCheckoutPage();
			Assert.assertTrue(poCheckoutPage.isCheckoutPageDisplayed());
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getErrorMessage());
			test.log(Status.FAIL, "Automation Execution was interrupted: " + e.getErrorMessage());
			log.fatal("Automation Execution was interrupted: " + e.getErrorMessage());
		}
		log.info("verifyNavigationToCheckoutPage() is completed");

	}

	@Test(dependsOnMethods = { "verifyNavigationToCheckoutPage" })
	public void verifyAccountCreation() {
		log.info("verifyAccountCreation() is invoked");
		try {
			test = extent.createTest("verifyAccountCreation", driver.get().getCapabilities().getBrowserName());
			poCheckoutPage.proceedToCheckout();
			poCheckoutPage.setEmailAddress(data.get("email"));
			poCheckoutPage.createAnAccount();
			poCheckoutPage.setFirstName(data.get("firstName"));
			poCheckoutPage.setLastName(data.get("lastName"));
			poCheckoutPage.setPassword(data.get("password"));
			poCheckoutPage.selectBirthDay(data.get("birthDay"));
			poCheckoutPage.selectBirthMonth(data.get("birthMonth"));
			poCheckoutPage.selectBirthYear(data.get("birthYear"));
			poCheckoutPage.setAddressLine1(data.get("addressLine1"));
			poCheckoutPage.setCity(data.get("city"));
			poCheckoutPage.selectState(data.get("state"));
			poCheckoutPage.selectCountry(data.get("country"));
			poCheckoutPage.setPostalCode(data.get("postalCode"));
			poCheckoutPage.setMobilePhone(data.get("phoneNumber"));
			poCheckoutPage.setAddressAlias(data.get("addressAlias"));
			poCheckoutPage.register();
			// Assert.assertTrue(poCheckoutPage.isUseSameAddressOptionDisplayed());
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getErrorMessage());
			test.log(Status.FAIL, "Automation Execution was interrupted: " + e.getErrorMessage());
			log.fatal("Automation Execution was interrupted: " + e.getErrorMessage());
		}
		log.info("verifyAccountCreation() is completed");

	}

	@Test(dependsOnMethods = { "verifyAccountCreation" })
	public void verifyOrder() {
		log.info("verifyOrder() is invoked");
		try {
			test = extent.createTest("verifyOrder", driver.get().getCapabilities().getBrowserName());
			poCheckoutPage.proceedToCheckout();
			poCheckoutPage.agreeToTermsAndService();
			poCheckoutPage.proceedToCheckoutOnShipping();
			String productName = poCheckoutPage.getProductNameOnConfirmOrderScreen();
			Assert.assertEquals(productName, data.get("searchResult"));
			if (productName.equalsIgnoreCase("Printed Chiffon Dress")) {
				test.log(Status.PASS, "Product Name matches on CONFIRM ORDER page\n" + "Expected Value: "
						+ data.get("searchResult") + "\n" + "Actual Value: " + productName);
			} else {
				test.log(Status.FAIL, "Product Name does not matche on CONFIRM ORDER page\n" + "Expected Value: "
						+ data.get("searchResult") + "\n" + "Actual Value: " + productName);

			}

			String productSize = poCheckoutPage.getProductSizeOnConfirmOrderScreen().strip();
			Assert.assertEquals(productSize, data.get("size"));
			if (productSize.equalsIgnoreCase("M")) {
				test.log(Status.PASS, "Product Size matches on CONFIRM ORDER page\n" + "Expected Value: "
						+ data.get("size") + "\n" + "Actual Value: " + productSize);
			} else {
				test.log(Status.FAIL, "Product Size does not matche on CONFIRM ORDER page\n" + "Expected Value: "
						+ data.get("size") + "\n" + "Actual Value: " + productSize);
			}
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getErrorMessage());
			test.log(Status.FAIL, "Automation Execution was interrupted: " + e.getErrorMessage());
			log.fatal("Automation Execution was interrupted: " + e.getErrorMessage());
		}
		log.info("verifyOrder() is completed");
	}

}
