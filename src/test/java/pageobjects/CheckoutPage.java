package pageobjects;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.Helper;

public class CheckoutPage {

	WebDriver driver;
	WebDriverWait wait;

	public CheckoutPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "cart_title")
	WebElement txtCartTitle;

	@FindBy(id = "cart_summary")
	WebElement tblCartContents;

	@FindBy(xpath = "//span[text()='Proceed to checkout']/i[contains(@class,'icon-chevron')]")
	WebElement btnProceedToCheckout;

	@FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Proceed to checkout')]/i[contains(@class,'icon-chevron')]")
	WebElement btnProceedToCheckoutOnShipping;
	
	
	@FindBy(id = "SubmitCreate")
	WebElement btnCreateAccount;

	@FindBy(id = "email_create")
	WebElement tbxEmail;

//	@FindBy(id="id_gender1")
//	WebElement titleMr;
//	
//	@FindBy(id="id_gender2")
//	WebElement titleMrs;

	@FindBy(id = "customer_firstname")
	WebElement tbxFirstName;

	@FindBy(id = "customer_lastname")
	WebElement tbxLastName;

	@FindBy(id = "passwd")
	WebElement tbxPassword;

	@FindBy(id = "days")
	WebElement drpDayofBirth;

	@FindBy(id = "months")
	WebElement drpMonthofBirth;

	@FindBy(id = "years")
	WebElement drpYearsofBirth;

//	@FindBy(id="firstname")
//	WebElement addressFirstName;
//
//	@FindBy(id="lastname")
//	WebElement addressLastName;

	@FindBy(id = "address1")
	WebElement tbxAddress1;

	@FindBy(id = "city")
	WebElement tbxCity;

	@FindBy(id = "id_state")
	WebElement drpState;

	@FindBy(id = "postcode")
	WebElement tbxPostalCode;

	@FindBy(id = "id_country")
	WebElement drpCountry;

	@FindBy(id = "phone_mobile")
	WebElement tbxMobilePhoneNumber;

	@FindBy(id = "alias")
	WebElement tbxAddressAlias;

	@FindBy(id = "submitAccount")
	WebElement btnRegister;
	
	@FindBy(id="addressesAreEquals")
	WebElement cbxUseSameAddress;
	
	@FindBy(id="cgv")
	WebElement cbxTermsOfService;
	
	@FindBy(css="table#cart_summary p.product-name a")
	WebElement lnkConfirmOrderProductName;
	
	@FindBy(xpath="//table[@id='cart_summary']//p[@class='product-name']/following-sibling::small/a")
	WebElement txtConfirmOrderProductSize;

	public boolean isCheckoutPageDisplayed() {
		return (Helper.isDisplayed(driver, wait, tblCartContents) & Helper.isDisplayed(driver, wait, txtCartTitle));
	}

	public void proceedToCheckout() {
		Helper.click(driver, wait, btnProceedToCheckout);
	}
	
	public void proceedToCheckoutOnShipping() {
		Helper.click(driver, wait, btnProceedToCheckoutOnShipping);
	}

	public boolean isSigninPageDisplayed() {
		return Helper.isDisplayed(driver, wait, btnCreateAccount);
	}

	public void setEmailAddress(String email) {
		Helper.write(driver, wait, tbxEmail, email);
	}

	public void createAnAccount() {
		Helper.click(driver, wait, btnCreateAccount);
	}

	public void setFirstName(String firstName) {
		Helper.write(driver, wait, tbxFirstName, firstName);

	}

	public void setLastName(String LastName) {
		Helper.write(driver, wait, tbxLastName, LastName);
	}

	public void setPassword(String password) {
		Helper.write(driver, wait, tbxPassword, password);
	}

	public void selectBirthDay(String day) {
		Helper.selectValueByValue(driver, wait, drpDayofBirth, day);
	}
	
	public void selectBirthMonth(String Month) {
		Helper.selectValueByValue(driver, wait, drpMonthofBirth, Month);
		
	}
	
	public void selectBirthYear(String Year) {
		Helper.selectValueByValue(driver, wait, drpYearsofBirth, Year);
		
	}
	
	public void setAddressLine1(String addressLine1) {
		Helper.write(driver, wait, tbxAddress1, addressLine1);
		
	}
	
	public void setCity(String city) {
		Helper.write(driver, wait, tbxCity, city);
		
	}
	
	public void selectState(String state) {
		Helper.selectValueByName(driver, wait, drpState, state);
	}
	
	public void setPostalCode(String postalCode) {
		Helper.write(driver, wait, tbxPostalCode, postalCode);
		
	}
	
	public void selectCountry(String country) {
		Helper.selectValueByName(driver, wait, drpCountry, country);

	}
	
	public void setMobilePhone(String phoneNumber) {
		Helper.write(driver, wait, tbxMobilePhoneNumber, phoneNumber);
		
	}
	
	public void setAddressAlias(String addressAlias) {
		Helper.write(driver, wait, tbxAddressAlias, addressAlias);
		
	}
	
	public void register() {
		Helper.click(driver, wait, btnRegister);
	}
	
	public boolean isUseSameAddressOptionDisplayed() {
		return Helper.isDisplayed(driver, wait, cbxUseSameAddress);
	}
	
	public void agreeToTermsAndService(){
		cbxTermsOfService.click();
		//Helper.click(driver, wait, cbxTermsOfService);
		
	}
	
	public String getProductNameOnConfirmOrderScreen() {
		return lnkConfirmOrderProductName.getText();
	}
	
	public String getProductSizeOnConfirmOrderScreen() {
		String[] productDetails = txtConfirmOrderProductSize.getText().split(",");
		return productDetails[1].split(":")[1];
	}
}
