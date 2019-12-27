package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AutomationException;
import framework.BrowserUtils;

/**
 * This class characterizes Home page of automation practice application In
 * doing so is declares and initializes all the useful WebElements required for
 * test script and also implements re-usable methods dealing with
 * functionalities on Home page
 */
public class HomePage extends BasePage {

	private static final Logger log = Logger.getLogger(HomePage.class);
//	WebgetDriver() getDriver();
//	WebgetDriver()Wait wait;
	QuickView poQuickView = null;

	@FindBy(xpath = "//img[@class='logo img-responsive']")
	WebElement imgLogo;

//	@FindBy(xpath = "//li[@class='sfHover']//ul//li//a[contains(text(),'Summer Dresses')]")
//	WebElement submenu_summerDresses;
//
//	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 last-in-line last-line first-item-of-tablet-line last-item-of-mobile-line last-mobile-line hovered']//img[@class='replace-2x img-responsive']")
//	WebElement chiffon_picture;

	@FindBy(xpath = "//a[@class='quick-view']")
	List<WebElement> lnkQuickView;

	@FindBy(xpath = "//a[@title='View my shopping cart']")
	WebElement lnkViewShoppingCart;

	@FindBy(id = "button_order_cart")
	WebElement btnCheckout;

	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement lnkProceedToCheckOut;

	@FindBy(id = "email_create")
	WebElement emailAddress;

	@FindBy(xpath = "//form[@id='create-account_form']//span[1]")
	WebElement createAccount;

	@FindBy(css = "iframe.fancybox-iframe")
	WebElement ifrQuickView;

	@FindBy(css = "i.icon-ok")
	WebElement icnOK;

	@FindBy(id = "layer_cart_product_title")
	WebElement txtProductNameOnCartSuccess;

	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']//span[1]")
	WebElement btnContinueShopping;

	/**
	 * This constructor helps instantiates the home page object and along with it
	 * assigns WebDriver instance to corresponding references for all the page
	 * objects and utilities. It also initializes the page factory objects.
	 * 
	 * @param driver
	 * @author Mitulsinh Vaghela
	 */
	public HomePage(WebDriver driver) {
		super(driver);
//		this.getDriver() = getDriver();
//		this.wait = wait;
		PageFactory.initElements(getDriver(), this);
	}

	/**
	 * This method launches the AUT
	 * 
	 * @param url of AUT
	 * @author Mitulsinh Vaghela
	 */
	public void launchApp(String url) throws AutomationException {
		this.getDriver().get(url);

	}

	/**
	 * This method moves the mouse over first level menu items - Women, Dresses &
	 * T-shirts
	 * 
	 * @param menu name of the menu displayed on screen
	 * @author Mitulsinh Vaghela
	 */
	public void hoverOverMenu(String menu) throws AutomationException {
		String locXpMenu = "//a[@class='sf-with-ul'][contains(text(),'" + menu + "')]";
		BrowserUtils.moveMouseOverElement(getDriver().findElement(By.xpath(locXpMenu)));
	}

	/**
	 * This method checks if "your logo new experience" is displayed This helps in
	 * validating that AUT has been launched successfully
	 * 
	 * @return boolean true if logo is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isLogoDisplayed() throws AutomationException {
		return BrowserUtils.isDisplayed(imgLogo);
	}

	/**
	 * This method clicks on 2nd level menu items once user hovers over main menu
	 * and 2nd level menu items are displayed
	 * 
	 * @param subMenu name of the sub menu displayed on screen
	 * @author Mitulsinh Vaghela
	 */
	public void clickOnSubMenu(String subMenu) throws AutomationException {
		String locXpSubMenu = "//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'"
				+ subMenu + "')]";
		BrowserUtils.click(getDriver().findElement(By.xpath(locXpSubMenu)));
	}

	/**
	 * This method checks if the results displayed match to the product category
	 * selected by the user This helps in validating that correct results are
	 * displayed
	 * 
	 * @param category product category selected by the user
	 * @return boolean true if correct results are displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isCategoryResultsDisplayed(String category) throws AutomationException {
		String locXpCatgResult = "//span[@class='cat-name' and contains(text(),'" + category + "')]";
		return BrowserUtils.isDisplayed(getDriver().findElement(By.xpath(locXpCatgResult)));
	}

	/**
	 * This method hovers mouse over a particular product on the product results
	 * page
	 * 
	 * @param productName name of the product as displayed on the screen
	 * @author Mitulsinh Vaghela
	 */
	public void hoverOverSearchResult(String productName) throws AutomationException {
		String locXpSearchResult = "//div[@class='right-block']//a[@class='product-name'][contains(text(),'"
				+ productName + "')]";
		BrowserUtils.moveMouseOverElement(getDriver().findElement(By.xpath(locXpSearchResult)));
	}

	/**
	 * This method checks if quick view option is displayed when hovered over a
	 * product on results page
	 * 
	 * @return boolean true if quick view option is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isQuickViewDisplayed() throws AutomationException {
		Boolean displayFlag = false;
		for (WebElement elem : lnkQuickView) {
			if (BrowserUtils.isDisplayed(elem)) {
				displayFlag = true;
			}
		}
		return displayFlag;
	}

	/**
	 * This method clicks on Quick View option displayed for the product
	 * 
	 * @return QuickView returns reference to quick view page object
	 * @author Mitulsinh Vaghela
	 */
	public QuickView clickOnQuickView() throws AutomationException {
		for (WebElement elem : lnkQuickView) {
			if (BrowserUtils.isDisplayed(elem)) {
				BrowserUtils.click(elem);
			}
		}
		return new QuickView(ifrQuickView);

	}

	/**
	 * This method checks product has been added to the cart upon clicking add to
	 * cart button
	 * 
	 * @return boolean true if success modal window is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isAddToCartSuccessful() throws AutomationException {
		return (BrowserUtils.isDisplayed(icnOK) & BrowserUtils.isDisplayed(txtProductNameOnCartSuccess));
	}

	/**
	 * This method clicks on continue shopping button displayed on the modal window
	 * that is displayed after successfully adding a product to cart.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void continueShopping() throws AutomationException {
		BrowserUtils.click(btnContinueShopping);
	}

	/**
	 * This method takes the user to the checkout page. It clicks on check out
	 * button displayed after hovering over my cart
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public CheckoutPage goToCheckoutPage() throws AutomationException {
		BrowserUtils.moveMouseOverElement(lnkViewShoppingCart);
		BrowserUtils.click(btnCheckout);
		return new CheckoutPage();
	}

}
