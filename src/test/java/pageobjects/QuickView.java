package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.Helper;

public class QuickView {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="group_1")
	WebElement drpSize;

	@FindBy(name = "Submit")
	WebElement btnAddToCart;

	
	public QuickView(WebDriver driver, WebDriverWait wait, WebElement elem){
		this.driver = driver;
		this.wait = wait;
		Helper.switchToFrame(driver, wait, elem);
		PageFactory.initElements(driver, this);
	}
	
	public void selectProductSize(String productSize) {
		Helper.selectValueByName(driver, wait, drpSize, productSize);
	}
	
	public void addToCart() {
		Helper.click(driver, wait, btnAddToCart);
	}
	

	

}
