package testscripts;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import framework.BaseTest;
import pageobjects.CheckoutPage;
import pageobjects.HomePage;
import pageobjects.QuickView;

public class Test_Ecom extends BaseTest {
	
	HomePage poHomePage;
	QuickView poQuickView;
	CheckoutPage poCheckoutPage;
	
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

	@Parameters({"URL"})
	@Test(priority = 1)
	public void verifyLaunchEcomApp(String url) {
		poHomePage = new HomePage(getDriver(),wait);
		test = extent.createTest("launchEcommApp",driver.get().getCapabilities().getBrowserName());
		poHomePage.launchApp(url);
		Assert.assertTrue(poHomePage.isLogoDisplayed());
	}
	
	@Test(priority = 2,dependsOnMethods = {"verifyLaunchEcomApp"} )
	public void verifyNavigationToACategoryUsingMenu() {
		test = extent.createTest("navigateToSummerDressesUsingMenu",driver.get().getCapabilities().getBrowserName());
		poHomePage.hoverOverMenu("Women");
		poHomePage.clickOnSubMenu("Summer Dresses");
		Assert.assertTrue(poHomePage.isCategoryResultsDisplayed("Summer Dresses"));
	}
	
	@Test(dependsOnMethods = {"verifyNavigationToACategoryUsingMenu"})
	public void verifyMouseOverParticularDressShowsQuickView() {
		test = extent.createTest("verifyMouseOverParticularDressShowsQuickView",driver.get().getCapabilities().getBrowserName());
		poHomePage.hoverOverSearchResult("Printed Chiffon Dress");
		Assert.assertTrue(poHomePage.isQuickViewDisplayed());
	} 
	
	@Test(dependsOnMethods = {"verifyMouseOverParticularDressShowsQuickView"})
	public void  verifyProductConfigurationAndAddingToCart() {
		test = extent.createTest("verifyProductConfigurationAndAddingToCart",driver.get().getCapabilities().getBrowserName());
		poQuickView = poHomePage.clickOnQuickView();
		poQuickView.selectProductSize("M");
		poQuickView.addToCart();
		Assert.assertTrue(poHomePage.isAddToCartSuccessful());
		poHomePage.continueShopping();
	}
	
	@Test(dependsOnMethods = {"verifyProductConfigurationAndAddingToCart"})
	public void verifyNavigationToCheckoutPage() {
		test = extent.createTest("verifyNavigationToCheckoutPage",driver.get().getCapabilities().getBrowserName());
		poCheckoutPage = poHomePage.goToCheckoutPage();
		Assert.assertTrue(poCheckoutPage.isCheckoutPageDisplayed());
		
	}
	
	@Test(dependsOnMethods = {"verifyNavigationToCheckoutPage"})
	public void verifyAccountCreation() {
		test = extent.createTest("verifyAccountCreation",driver.get().getCapabilities().getBrowserName());
		poCheckoutPage.proceedToCheckout();
		poCheckoutPage.setEmailAddress("amigo11@xyz.com");
		poCheckoutPage.createAnAccount();
		poCheckoutPage.setFirstName("James");
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
		
	}
	
	@Test(dependsOnMethods = {"verifyAccountCreation"})
	public void verifyOrder() {
		test = extent.createTest("verifyOrder",driver.get().getCapabilities().getBrowserName());
		poCheckoutPage.proceedToCheckout();
		poCheckoutPage.agreeToTermsAndService();
		poCheckoutPage.proceedToCheckoutOnShipping();
		if(poCheckoutPage.getProductNameOnConfirmOrderScreen().equalsIgnoreCase("Printed Chiffon Dress")){
			test.log(Status.PASS, "Product Name matches on CONFIRM ORDER page");
		}else {
			test.log(Status.FAIL, "Product Name does not match on CONFIRM ORDER page");
			
		}
		
		if(poCheckoutPage.getProductSizeOnConfirmOrderScreen().strip().equalsIgnoreCase("M")){
			test.log(Status.PASS, "Product Size matches on CONFIRM ORDER page");
		}else {
			test.log(Status.FAIL, "Product Size does not match on CONFIRM ORDER page");
			
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
