package pageobjects;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.BrowserUtils;

public class CheckoutPage extends BasePage {

	/**
	 * This constructor instantiates the checkout page object and 
	 * initializes the page factory objects. 
	 * @author Mitulsinh Vaghela
	 */
	public CheckoutPage() {
		PageFactory.initElements(getDriver(), this);
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

	/**
	 * This method checks if checkout page is displayed
	 * @return boolean true if checkout page is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isCheckoutPageDisplayed() {
		return (BrowserUtils.isDisplayed(tblCartContents) & BrowserUtils.isDisplayed(txtCartTitle));
	}

	/**
	 * This method clicks on proceed to checkout button displayed on different steps
	 * in the check out process except Shipping page. For shipping page, 
	 * @see proceedToCheckoutOnShipping method.
	 * @author Mitulsinh Vaghela
	 */
	public void proceedToCheckout() {
		BrowserUtils.click(btnProceedToCheckout);
	}
	
	/**
	 * This method clicks on proceed to checkout button displayed on shipping page
	 * @author Mitulsinh Vaghela
	 */
	public void proceedToCheckoutOnShipping() {
		BrowserUtils.click(btnProceedToCheckoutOnShipping);
	}

	/**
	 * This method checks if sign in section is displayed in the check out process
	 * @return boolean true if sign in section is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isSigninPageDisplayed() {
		return BrowserUtils.isDisplayed(btnCreateAccount);
	}

	/**
	 * This method sets value in Email Address textbox displayed on sign in section
	 * @param email email address
	 * @author Mitulsinh Vaghela
	 */
	public void setEmailAddress(String email) {
		BrowserUtils.write(tbxEmail, email);
	}

	/**
	 * This method clicks on Create Account button displayed on sign in section
	 * @author Mitulsinh Vaghela
	 */
	public void createAnAccount() {
		BrowserUtils.click(btnCreateAccount);
	}

	/**
	 * This method sets value in First Name textbox on register user form
	 * @param firstName first name
	 * @author Mitulsinh Vaghela
	 */
	public void setFirstName(String firstName) {
		BrowserUtils.write(tbxFirstName, firstName);

	}

	/**
	 * This method sets value in Last Name textbox on register user form
	 * @param LastName last name
	 * @author Mitulsinh Vaghela
	 */
	public void setLastName(String LastName) {
		BrowserUtils.write(tbxLastName, LastName);
	}

	/**
	 * This method sets value in password textbox on register user form
	 * @param password password
	 * @author Mitulsinh Vaghela
	 */
	public void setPassword(String password) {
		BrowserUtils.write(tbxPassword, password);
	}

	/**
	 * This method selects a value in Birth Day dropdown on register user form
	 * @param day Birth day - 1,2,3...31
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthDay(String day) {
		BrowserUtils.selectValueByValue( drpDayofBirth, day);
	}
	
	
	/**
	 * This method selects a value in Month dropdown on register user form
	 * @param month Month - January, February so on.
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthMonth(String Month) {
		BrowserUtils.selectValueByValue( drpMonthofBirth, Month);
		
	}
	
	public void selectBirthYear(String Year) {
		BrowserUtils.selectValueByValue( drpYearsofBirth, Year);
		
	}
	
	public void setAddressLine1(String addressLine1) {
		BrowserUtils.write( tbxAddress1, addressLine1);
		
	}
	
	public void setCity(String city) {
		BrowserUtils.write( tbxCity, city);
		
	}
	
	public void selectState(String state) {
		BrowserUtils.selectValueByName( drpState, state);
	}
	
	public void setPostalCode(String postalCode) {
		BrowserUtils.write( tbxPostalCode, postalCode);
		
	}
	
	public void selectCountry(String country) {
		BrowserUtils.selectValueByName( drpCountry, country);

	}
	
	public void setMobilePhone(String phoneNumber) {
		BrowserUtils.write( tbxMobilePhoneNumber, phoneNumber);
		
	}
	
	public void setAddressAlias(String addressAlias) {
		BrowserUtils.write( tbxAddressAlias, addressAlias);
		
	}
	
	public void register() {
		BrowserUtils.click( btnRegister);
	}
	
	public boolean isUseSameAddressOptionDisplayed() {
		return BrowserUtils.isDisplayed( cbxUseSameAddress);
	}
	
	public void agreeToTermsAndService(){
		cbxTermsOfService.click();
		//Helper.click( cbxTermsOfService);
		
	}
	
	public String getProductNameOnConfirmOrderScreen() {
		return lnkConfirmOrderProductName.getText();
	}
	
	public String getProductSizeOnConfirmOrderScreen() {
		String[] productDetails = txtConfirmOrderProductSize.getText().split(",");
		return productDetails[1].split(":")[1];
	}
}
