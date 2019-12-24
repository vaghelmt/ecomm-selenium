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

@Test(groups = "buyProduct")
public class Test_Ecom extends BaseTest {

	HomePage poHomePage;
	QuickView poQuickView;
	CheckoutPage poCheckoutPage;
	private final HashMap<String, String> data;

	/**
	 * reference variable for logger
	 */
	private static final Logger log = Logger.getLogger("ecomm");
	
//	@DataProvider(name = "TD_Ecom")
//	public static Object[][] TestData_Test_Ecom() {
//		return new Object[][] { { new HashMap<String, String>() {
//			{
//				put("firstName", "James");
//				put("email", "amigo15@xyz.com");
//			}
//		} }, { new HashMap<String, String>() {
//			{
//				put("firstName", "Mahesh");
//				put("email", "amigo16@xyz.com");
//			}
//		} } };
//	}

	@DataProvider(name = "TD_Ecom")
	public static Iterator<Object[]> TestData_Test_Ecom() {
		List<HashMap<String, String>> listOfMap = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
//        HashMap<String,String> map2 = new HashMap<String,String>();
		map1.put("firstName", "James");
		map1.put("email", "amigo"+ ((int) Math.ceil(Math.random()*100)) + ((int) Math.ceil(Math.random()*100)) +"@xyz.com");
		log.info(map1.get("email"));
		//        map2.put("firstName", "Mahesh");
//        map2.put("email", "amigo17@xyz.com");
		listOfMap.add(map1);
//        listOfMap.add(map2);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (HashMap<String, String> map : listOfMap) {
			dp.add(new Object[] { map });
		}
		return dp.iterator();
	}

	@Factory(dataProvider = "TD_Ecom")
	public Test_Ecom(HashMap<String, String> data) {
		this.data = data;
	}

//	@Test(dataProvider="TD_Ecom")
//	public void loadTestDataForEcom(HashMap<String,String> data) {
//		test = extent.createTest("loadTestDataForEcom", driver.get().getCapabilities().getBrowserName());
//		this.data = data;
//	}

//	  @DataProvider(name = "URLS")
//	  public static Object[][] sites() {
//
//	        // The number of times data is repeated, test will be executed the same no. of times
//
//	        // Here it will execute two times
//
//	        return new Object[][] { { "https://www.google.com" }, { "https://www.facebook.com" }};
//
//	  }

	@Parameters({ "URL" })
	@Test
	public void verifyLaunchEcomApp(String url) {
		try {
			poHomePage = new HomePage(getDriver());
			test = extent.createTest("launchEcommApp", driver.get().getCapabilities().getBrowserName());
			poHomePage.launchApp(url);
			Assert.assertTrue(poHomePage.isLogoDisplayed());

		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getMessage());
			test.log(Status.FAIL, e.getErrorMessage());
		}

	}

	@Test(dependsOnMethods = { "verifyLaunchEcomApp" })
	public void verifyNavigationToACategoryUsingMenu() {
		try {
			test = extent.createTest("navigateToSummerDressesUsingMenu",
					driver.get().getCapabilities().getBrowserName());
			poHomePage.hoverOverMenu("Women");
			poHomePage.clickOnSubMenu("Summer Dresses");
			Assert.assertTrue(poHomePage.isCategoryResultsDisplayed("Summer Dresses"));
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getMessage());
			test.log(Status.FAIL, e.getErrorMessage());
		}
	}

	@Test(dependsOnMethods = { "verifyNavigationToACategoryUsingMenu" })
	public void verifyMouseOverParticularDressShowsQuickView() {
		try {
			test = extent.createTest("verifyMouseOverParticularDressShowsQuickView",
					driver.get().getCapabilities().getBrowserName());
			poHomePage.hoverOverSearchResult("Printed Chiffon Dress");
			Assert.assertTrue(poHomePage.isQuickViewDisplayed());
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getMessage());
			test.log(Status.FAIL, e.getErrorMessage());
		}
	}

	@Test(dependsOnMethods = { "verifyMouseOverParticularDressShowsQuickView" })
	public void verifyProductConfigurationAndAddingToCart() {
		try {
			test = extent.createTest("verifyProductConfigurationAndAddingToCart",
					driver.get().getCapabilities().getBrowserName());
			poQuickView = poHomePage.clickOnQuickView();
			poQuickView.selectProductSize("M");
			poQuickView.addToCart();
			Assert.assertTrue(poHomePage.isAddToCartSuccessful());
			poHomePage.continueShopping();
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getMessage());
			test.log(Status.FAIL, e.getErrorMessage());
		}
	}

	@Test(dependsOnMethods = { "verifyProductConfigurationAndAddingToCart" })
	public void verifyNavigationToCheckoutPage() {
		try {
			test = extent.createTest("verifyNavigationToCheckoutPage", driver.get().getCapabilities().getBrowserName());
			poCheckoutPage = poHomePage.goToCheckoutPage();
			Assert.assertTrue(poCheckoutPage.isCheckoutPageDisplayed());
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getMessage());
			test.log(Status.FAIL, e.getErrorMessage());
		}

	}

	@Test(dependsOnMethods = { "verifyNavigationToCheckoutPage" })
	public void verifyAccountCreation() {
		try {
			test = extent.createTest("verifyAccountCreation", driver.get().getCapabilities().getBrowserName());
			poCheckoutPage.proceedToCheckout();
			poCheckoutPage.setEmailAddress(data.get("email"));
			poCheckoutPage.createAnAccount();
			poCheckoutPage.setFirstName(data.get("firstName"));
			poCheckoutPage.setLastName("Bond");
			poCheckoutPage.setPassword("test@1234");
			poCheckoutPage.selectBirthDay("1");
			poCheckoutPage.selectBirthMonth("8");
			poCheckoutPage.selectBirthYear("2000");
			poCheckoutPage.setAddressLine1("105 Dundas Street");
			poCheckoutPage.setCity("Toronto");
			poCheckoutPage.selectState("Florida");
			poCheckoutPage.selectCountry("United States");
			poCheckoutPage.setPostalCode("45678");
			poCheckoutPage.setMobilePhone("7895671879");
			poCheckoutPage.setAddressAlias("fav address");
			poCheckoutPage.register();
			//Assert.assertTrue(poCheckoutPage.isUseSameAddressOptionDisplayed());
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getMessage());
			test.log(Status.FAIL, e.getErrorMessage());

		}

	}

	@Test(dependsOnMethods = { "verifyAccountCreation" })
	public void verifyOrder() {
		try {
			test = extent.createTest("verifyOrder", driver.get().getCapabilities().getBrowserName());
			poCheckoutPage.proceedToCheckout();
			poCheckoutPage.agreeToTermsAndService();
			poCheckoutPage.proceedToCheckoutOnShipping();
			if (poCheckoutPage.getProductNameOnConfirmOrderScreen().equalsIgnoreCase("Printed Chiffon Dress")) {
				test.log(Status.PASS, "Product Name matches on CONFIRM ORDER page");
			} else {
				test.log(Status.FAIL, "Product Name does not match on CONFIRM ORDER page");

			}

			if (poCheckoutPage.getProductSizeOnConfirmOrderScreen().strip().equalsIgnoreCase("M")) {
				test.log(Status.PASS, "Product Size matches on CONFIRM ORDER page");
			} else {
				test.log(Status.FAIL, "Product Size does not match on CONFIRM ORDER page");

			}
		} catch (AutomationException e) {
			Assert.fail("Automation Execution was interrupted: " + e.getMessage());
			test.log(Status.FAIL, e.getErrorMessage());
		}

	}

//	@Test(dataProvider = "URLS")
//	public void findDress(String url) {
//		//test.log(Status.PASS, "Find dress test is pass");
//		getDriver().navigate().to(url);
//		System.out.println("find dress: " + Thread.currentThread().getId() + driver.get().getCurrentUrl());
//		
//	}
//	
//	@Test(dataProvider = "URLS")
//	public void addToCart(String url) {
//		//test.log(Status.PASS, "Add to cart test is pass");
//		getDriver().navigate().to(url);
//		System.out.println("add to cart: " + Thread.currentThread().getId()+ driver.get().getCurrentUrl());
//	}

}
