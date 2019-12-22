package testscripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.BaseTest;
import pageobjects.HomePage;

public class Test_Ecom extends BaseTest {
	
	HomePage homePage;
	
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
		homePage = new HomePage(getDriver());
		test = extent.createTest("launchEcommApp",driver.get().getCapabilities().getBrowserName());
		homePage.launchApp(url);
		Assert.assertTrue(homePage.isLogoDisplayed());
	}
	
	@Test(priority = 2,dependsOnMethods = {"verifyLaunchEcomApp"} )
	public void verifyNavigationToACategoryUsingMenu() {
		test = extent.createTest("navigateToSummerDressesUsingMenu",driver.get().getCapabilities().getBrowserName());
		homePage.hoverOverMenu("Women");
		homePage.clickOnSubMenu("Summer Dresses");
		Assert.assertTrue(homePage.isCategoryResultsDisplayed("Summer Dresses"));
	}
	
	@Test(dependsOnMethods = {"verifyNavigationToACategoryUsingMenu"})
	public void verifyMouseOverParticularDressShowsQuickView() {
		homePage.hoverOverSearchResult("Printed Chiffon Dress");
		Assert.assertTrue(homePage.isQuickViewDisplayed());
	} 
	
	@Test(dependsOnMethods = {"verifyMouseOverParticularDressShowsQuickView"})
	public void  verifyProductConfigurationAndAddingToCart() {
		homePage.clickOnQuickView();
		homePage.selectProductSize("M");
		homePage.addToCart();
		Assert.assertTrue(homePage.isAddToCartSuccessful());
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
