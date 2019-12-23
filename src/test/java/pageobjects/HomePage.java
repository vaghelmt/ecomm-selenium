package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.Helper;

/**
 * This class characterizes Home page of automation practice application In
 * doing so is declares and initializes all the useful WebElements required for
 * test script and also implements re-usable methods dealing with
 * functionalities on Home page
 */
public class HomePage {

	WebDriver driver;
	WebDriverWait wait;
	QuickView poQuickView=null;

	@FindBy(xpath = "//img[@class='logo img-responsive']")
	WebElement logo;
	
	@FindBy(xpath = "//li[@class='sfHover']//ul//li//a[contains(text(),'Summer Dresses')]")
	WebElement submenu_summerDresses;

	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 last-in-line last-line first-item-of-tablet-line last-item-of-mobile-line last-mobile-line hovered']//img[@class='replace-2x img-responsive']")
	WebElement chiffon_picture;

	@FindBy(xpath = "//a[@class='quick-view']")
	List<WebElement> lnkQuickView;

	@FindBy(xpath = "//a[@title='View my shopping cart']")
	WebElement lnkViewShoppingCart;

	@FindBy(id="button_order_cart")
	WebElement btnCheckout;

	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOut;

	@FindBy(id = "email_create")
	WebElement emailAddress;

	@FindBy(xpath = "//form[@id='create-account_form']//span[1]")
	WebElement createAccount;

	@FindBy(css="iframe.fancybox-iframe")
	WebElement ifrQuickView;
	
	@FindBy(css="i.icon-ok")
	WebElement icnOK;
	
	@FindBy(id="layer_cart_product_title")
	WebElement txtProductNameOnCartSuccess;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']//span[1]")
	WebElement btnContinueShopping;
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	public void launchApp(String url) {
		this.driver.get(url);

	}

	public void hoverOverMenu(String menu) {
		String locXpMenu = "//a[@class='sf-with-ul'][contains(text(),'" + menu + "')]";
		Helper.moveMouseOverElement(driver, wait, driver.findElement(By.xpath(locXpMenu)));
	}

	public boolean isLogoDisplayed() {
		return Helper.isDisplayed(driver, wait, logo);
	}

	public void clickOnSubMenu(String subMenu) {
		String locXpSubMenu = "//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'" + subMenu + "')]";
		Helper.click(driver, wait, driver.findElement(By.xpath(locXpSubMenu)));
	}
	
	public boolean isCategoryResultsDisplayed(String category) {
		String locXpCatgResult = "//span[@class='cat-name' and contains(text(),'"+ category +"')]";
		return Helper.isDisplayed(driver, wait, driver.findElement(By.xpath(locXpCatgResult)));	
	}

	public void hoverOverSearchResult(String productName) {
		String locXpSearchResult = "//div[@class='right-block']//a[@class='product-name'][contains(text(),'"+ productName +"')]";
		Helper.moveMouseOverElement(driver, wait, driver.findElement(By.xpath(locXpSearchResult)));
	}
	
	public boolean isQuickViewDisplayed() {
		Boolean displayFlag = false;
		for(WebElement elem:lnkQuickView) {
			if(Helper.isDisplayed(driver, wait, elem)) {
				displayFlag = true;		
			}
		}
		return displayFlag;
	}

	public QuickView clickOnQuickView() {
		for(WebElement elem:lnkQuickView) {
			if(Helper.isDisplayed(driver, wait,  elem)) {
				Helper.click(driver, wait, elem);		
			}
		}
		return new QuickView(driver,wait,ifrQuickView);
		
	}
	
	public boolean isAddToCartSuccessful() {
		return (Helper.isDisplayed(driver, wait, icnOK) & Helper.isDisplayed(driver, wait,  txtProductNameOnCartSuccess));
	}
	
	public void continueShopping() {
		Helper.click(driver, wait, btnContinueShopping);
	}
	
	public CheckoutPage goToCheckoutPage() {
		Helper.moveMouseOverElement(driver, wait, lnkViewShoppingCart);
		Helper.click(driver, wait, btnCheckout);
		return new CheckoutPage(driver,wait);
	}

	
	
	
	
	

}
