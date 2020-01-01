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
 * @author Mitulsinh Vaghela
 * @version 1.0
 */
public class HomePage extends BasePage {
	
	/**
	 * logger reference variable
	 */
	private static final Logger log = Logger.getLogger(HomePage.class);

	/**reference variable to store quick view page object as and when it is returned
	 */
	private QuickView poQuickView = null;

	/**refers to image that is a logo which is displayed adjacent to search bar 
	 * (your logo - a new experience)
	 */
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	private WebElement imgLogo;

	/**
	 * refers to quick view links displayed for products on results page
	 */
	@FindBy(xpath = "//a[@class='quick-view']")
	private List<WebElement> lnkQuickView;

	//refers to the cart label displayed adjacent to search bar
	@FindBy(xpath = "//a[@title='View my shopping cart']")
	private WebElement lnkViewShoppingCart;

	/**refers to check out button that is displayed when a fly out appears when
	 * hovered over the cart label
	 */
	@FindBy(id = "button_order_cart")
	private WebElement btnCheckout;

	/**refers to proceed to checkout link displayed on add-to-cart successful 
	 * modal window
	 */
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	private WebElement lnkProceedToCheckOut;

	/**refers to email address field*/
	@FindBy(id = "email_create")
	private WebElement emailAddress;

	/**refers to the create account label*/
	@FindBy(xpath = "//form[@id='create-account_form']//span[1]")
	private WebElement createAccount;

	/**refers to the frame that contains the quick view for a product*/
	@FindBy(css = "iframe.fancybox-iframe")
	private WebElement ifrQuickView;

	/**refers to the ok icon displayed on the add-to-cart successful modal window*/
	@FindBy(css = "i.icon-ok")
	private WebElement icnOK;

	/**refers to the product name displayed on the add-to-cart successful modal*/
	@FindBy(id = "layer_cart_product_title")
	private WebElement txtProductNameOnCartSuccess;

	/**refers to continue shopping button on add-to-cart successful modal*/
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']//span[1]")
	private WebElement btnContinueShopping;

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
		log.info("Home page constructor is invoked");
		PageFactory.initElements(getDriver(), this);
		log.info("Home page objects are initialized successfully and constructor ends");
	}

	/**
	 * This method launches the AUT
	 * 
	 * @param url of AUT
	 * @author Mitulsinh Vaghela
	 */
	public void launchApp(String url) throws AutomationException {
		log.info("launchApp(String url) is invoked");
		this.getDriver().get(url);
		log.info("launchApp(String url) is completed");

	}

	/**
	 * This method moves the mouse over first level menu items - Women, Dresses &
	 * T-shirts
	 * 
	 * @param menu name of the menu displayed on screen
	 * @author Mitulsinh Vaghela
	 */
	public void hoverOverMenu(String menu) throws AutomationException {
		log.info("hoverOverMenu(String menu) is invoked");
		/**refers to locator for Main menu */
		String locXpMenu = "//a[@class='sf-with-ul'][contains(text(),'" + menu + "')]";
		BrowserUtils.moveMouseOverElement(getDriver().findElement(By.xpath(locXpMenu)));
		log.info("hoverOverMenu(String menu) is completed");
	}

	/**
	 * This method checks if "your logo new experience" is displayed This helps in
	 * validating that AUT has been launched successfully
	 * 
	 * @return boolean true if logo is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isLogoDisplayed() throws AutomationException {
		log.info("isLogoDisplayed() is invoked and boolean will be returned");
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
		log.info("isLogoDisplayed() is invoked");
		/**refers to locator for submenu*/
		String locXpSubMenu = "//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'"
				+ subMenu + "')]";
		BrowserUtils.click(getDriver().findElement(By.xpath(locXpSubMenu)));
		log.info("isLogoDisplayed() is completed");
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
		log.info("isCategoryResultsDisplayed(String category) is invoked and boolean will be returned");
		/**refers to locator for category results*/
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
		log.info("hoverOverSearchResult(String productName) is invoked");
		String locXpSearchResult = "//div[@class='right-block']//a[@class='product-name'][contains(text(),'"
				+ productName + "')]";
		BrowserUtils.moveMouseOverElement(getDriver().findElement(By.xpath(locXpSearchResult)));
		log.info("hoverOverSearchResult(String productName) is completed");
	}

	/**
	 * This method checks if quick view option is displayed when hovered over a
	 * product on results page
	 * 
	 * @return boolean true if quick view option is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isQuickViewDisplayed() throws AutomationException {
		log.info("isQuickViewDisplayed() is invoked and boolean will be returned");
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
		log.info("clickOnQuickView() is invoked and boolean will be returned");
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
		log.info("isAddToCartSuccessful() is invoked and boolean will be returned");
		return (BrowserUtils.isDisplayed(icnOK) & BrowserUtils.isDisplayed(txtProductNameOnCartSuccess));
	}

	/**
	 * This method clicks on continue shopping button displayed on the modal window
	 * that is displayed after successfully adding a product to cart.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void continueShopping() throws AutomationException {
		log.info("continueShopping() is invoked");
		BrowserUtils.click(btnContinueShopping);
		log.info("continueShopping() is completed");
	}

	/**
	 * This method takes the user to the checkout page. It clicks on check out
	 * button displayed after hovering over my cart
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public CheckoutPage goToCheckoutPage() throws AutomationException {
		log.info("goToCheckoutPage() is invoked and boolean will be returned");
		BrowserUtils.moveMouseOverElement(lnkViewShoppingCart);
		BrowserUtils.click(btnCheckout);
		return new CheckoutPage();
	}

}
