package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.Helper;

/**
 * This class characterizes Home page of automation practice application In
 * doing so is declares and initializes all the useful WebElements required for
 * test script and also implements re-usable methods dealing with
 * functionalities on Home page
 */
public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//img[@class='logo img-responsive']")
	WebElement logo;
	
	@FindBy(xpath = "//li[@class='sfHover']//ul//li//a[contains(text(),'Summer Dresses')]")
	WebElement submenu_summerDresses;

	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 last-in-line last-line first-item-of-tablet-line last-item-of-mobile-line last-mobile-line hovered']//img[@class='replace-2x img-responsive']")
	WebElement chiffon_picture;

	@FindBy(xpath = "//a[@class='quick-view']")
	List<WebElement> quickView;

	@FindBy(id = "group_1")
	WebElement drpSize;

	@FindBy(name = "Submit")
	WebElement btnAddToCart;

	@FindBy(css="i.icon-ok")
	WebElement icnOK;
	
	@FindBy(id="layer_cart_product_title")
	WebElement txtProductNameOnCartSuccess;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']//span[1]")
	WebElement continueShopping;

	@FindBy(xpath = "//body[@id='category']/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@class='col-sm-4 clearfix']/div[@class='shopping_cart']/a[1]")
	WebElement cart;

	@FindBy(xpath = "//span[contains(text(),'Check out')]")
	WebElement checkout;

	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOut;

	@FindBy(id = "email_create")
	WebElement emailAddress;

	@FindBy(xpath = "//form[@id='create-account_form']//span[1]")
	WebElement createAccount;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void launchApp(String url) {
		this.driver.get(url);

	}

	public void hoverOverMenu(String menu) {
		String locXpMenu = "//a[@class='sf-with-ul'][contains(text(),'" + menu + "')]";
		Helper.moveMouseOverElement(driver, driver.findElement(By.xpath(locXpMenu)));
	}

	public boolean isLogoDisplayed() {
		return Helper.isDisplayed(driver, logo);
	}

	public void clickOnSubMenu(String subMenu) {
		String locXpSubMenu = "//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'" + subMenu + "')]";
		Helper.click(driver, driver.findElement(By.xpath(locXpSubMenu)));
	}
	
	public boolean isCategoryResultsDisplayed(String category) {
		String locXpCatgResult = "//span[@class='cat-name' and contains(text(),'"+ category +"')]";
		return Helper.isDisplayed(driver, driver.findElement(By.xpath(locXpCatgResult)));	
	}

	public void hoverOverSearchResult(String productName) {
		String locXpSearchResult = "//div[@class='right-block']//a[@class='product-name'][contains(text(),'"+ productName +"')]";
		Helper.moveMouseOverElement(driver, driver.findElement(By.xpath(locXpSearchResult)));
	}
	
	public boolean isQuickViewDisplayed() {
		Boolean displayFlag = false;
		for(WebElement elem:quickView) {
			if(Helper.isDisplayed(driver, elem)) {
				displayFlag = true;		
			}
		}
		return displayFlag;
	}

	public void clickOnQuickView() {
		for(WebElement elem:quickView) {
			if(Helper.isDisplayed(driver, elem)) {
				Helper.click(driver, elem);		
			}
		}
		
	}
	
	public void selectProductSize(String productSize) {
		Helper.selectValueByName(driver, drpSize, productSize);
	}
	
	public void addToCart() {
		Helper.click(driver, btnAddToCart);
	}
	
	public boolean isAddToCartSuccessful() {
		return (Helper.isDisplayed(driver, icnOK) & Helper.isDisplayed(driver, txtProductNameOnCartSuccess));
	}
	
	
	
	
	

}
