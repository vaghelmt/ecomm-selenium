package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.BrowserUtils;

public class QuickView extends BasePage {

	/**reference varaible for logger*/
	private static final Logger log = Logger.getLogger(QuickView.class);

	/**refers to prodcut size dropdown */
	@FindBy(id = "group_1")
	private WebElement drpSize;

	/**refers to add to cart button*/
	@FindBy(name = "Submit")
	private WebElement btnAddToCart;

	/**
	 * This constructor instantiates the quick view page object and switches the
	 * frame as quick view modal window is present in a different frame. It also
	 * initializes the page factory objects.
	 * 
	 * @param elem iframe WebElement holding the quick view modal window
	 * @author Mitulsinh Vaghela
	 */
	public QuickView(WebElement elem) {
		log.info("Quick View page constructor invoked");
		BrowserUtils.switchToFrame(elem);
		log.info("Switched to Quick view frame successfully");
		PageFactory.initElements(getDriver(), this);
		log.info("Quick view page objects initialized successfully and constructor ends");
	}

	/**
	 * This method selects one of the product sizes from the dropdown
	 * 
	 * @param productSize size of the product present in dropdown
	 * @author Mitulsinh Vaghela
	 */
	public void selectProductSize(String productSize) {
		log.info("selectProductSize(String productSize) is invoked");
		BrowserUtils.selectValueByName(drpSize, productSize);
		log.info("selectProductSize(String productSize) is completed");
	}

	/**
	 * This method adds a product to cart from the quick view modal window
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void addToCart() {
		log.info("addToCart() is invoked");
		BrowserUtils.click(btnAddToCart);
		BrowserUtils.switchToDefaultContent();
		log.info("addToCart() is completed");
	}

}
