package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.BrowserUtils;

public class QuickView extends BasePage{
	
	private static final Logger log = Logger.getLogger(QuickView.class);
	
	@FindBy(id="group_1")
	WebElement drpSize;

	@FindBy(name = "Submit")
	WebElement btnAddToCart;

	/**
	 * This constructor instantiates the quick view page object and
	 * switches the frame as quick view modal window is present in a
	 * different frame. It also initializes the page factory objects. 
	 * @param elem iframe WebElement holding the quick view modal window
	 * @author Mitulsinh Vaghela
	 */
	public QuickView(WebElement elem){
		BrowserUtils.switchToFrame(elem);
		PageFactory.initElements(getDriver(), this);
	}
	
	/**
	 * This method selects one of the product sizes from the dropdown
	 * @param productSize size of the product present in dropdown
	 * @author Mitulsinh Vaghela
	 */
	public void selectProductSize(String productSize) {
		BrowserUtils.selectValueByName( drpSize, productSize);
	}
	
	/**
	 * This method adds a product to cart from the quick view modal window
	 * @author Mitulsinh Vaghela
	 */
	public void addToCart() {
		BrowserUtils.click( btnAddToCart);
		BrowserUtils.switchToDefaultContent();
	}
	

	

}
