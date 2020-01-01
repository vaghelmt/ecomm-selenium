package pageobjects;

import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.AutomationException;
import framework.BrowserUtils;

public class CheckoutPage extends BasePage {

	/**logger reference variable*/
	private static final Logger log = Logger.getLogger(CheckoutPage.class);

	/**
	 * This constructor instantiates the checkout page object and initializes the
	 * page factory objects.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public CheckoutPage() {
		log.info("CheckoutPage constructor is invoked");
		PageFactory.initElements(getDriver(), this);
		log.info("Checkout page object intialized successfully and constrcutor ends");
	}

	/**refers to the cart label displayed on the check out page*/
	@FindBy(id = "cart_title")
	private WebElement txtCartTitle;

	/**refers to table that contains the items in the cart*/
	@FindBy(id = "cart_summary")
	private WebElement tblCartContents;

	/**refers to proceed to check out button on all the sections of check out page
	 * except shipping section
	 */
	@FindBy(xpath = "//span[text()='Proceed to checkout']/i[contains(@class,'icon-chevron')]")
	private WebElement btnProceedToCheckout;

	/**refers to proceed to check out button on shipping section of check out page*/
	@FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Proceed to checkout')]/i[contains(@class,'icon-chevron')]")
	private WebElement btnProceedToCheckoutOnShipping;

	/**refers to create account label displayed on the Sign in section*/
	@FindBy(id = "SubmitCreate")
	private WebElement btnCreateAccount;

	/**refers to email address textbox displayed on the Sign in section*/
	@FindBy(id = "email_create")
	private WebElement tbxEmail;
	
	/**refers to first name textbox displayed on the registration form*/
	@FindBy(id = "customer_firstname")
	private WebElement tbxFirstName;

	/**refers to last name textbox displayed on the registration form*/
	@FindBy(id = "customer_lastname")
	private WebElement tbxLastName;

	/**refers to password textbox displayed on the registration form*/
	@FindBy(id = "passwd")
	private WebElement tbxPassword;

	/**refers to Birth Day dropdown displayed on the registration form*/
	@FindBy(id = "days")
	private WebElement drpDayofBirth;

	/**refers to Birth Month dropdown displayed on the registration form*/
	@FindBy(id = "months")
	private WebElement drpMonthofBirth;

	/**refers to Birth Year dropdown displayed on the registration form*/
	@FindBy(id = "years")
	private WebElement drpYearsofBirth;

	/**refers to Address textbox displayed on the registration form*/
	@FindBy(id = "address1")
	private WebElement tbxAddress1;

	/**refers to city textbox displayed on the registration form*/
	@FindBy(id = "city")
	private WebElement tbxCity;

	/**refers to State dropdown displayed on the registration form*/
	@FindBy(id = "id_state")
	private WebElement drpState;

	/**refers to Zip/Postal code textbox displayed on the registration form*/
	@FindBy(id = "postcode")
	private WebElement tbxPostalCode;

	/**refers to country dropdown displayed on the registration form*/
	@FindBy(id = "id_country")
	private WebElement drpCountry;

	/**refers to mobile phone textbox displayed on the registration form*/
	@FindBy(id = "phone_mobile")
	private WebElement tbxMobilePhoneNumber;

	/**refers to Address Alias textbox displayed on the registration form*/
	@FindBy(id = "alias")
	private WebElement tbxAddressAlias;

	/**refers to register button displayed on the registration form*/
	@FindBy(id = "submitAccount")
	private WebElement btnRegister;

	/**refers to Use same address checkbox displayed on the shipping section*/
	@FindBy(id = "addressesAreEquals")
	private WebElement cbxUseSameAddress;

	/**refers to terms of service checkbox displayed on the shipping section*/
	@FindBy(id = "cgv")
	private WebElement cbxTermsOfService;

	/**refers to prodcut name displayed on confirm section*/
	@FindBy(css = "table#cart_summary p.product-name a")
	private WebElement lnkConfirmOrderProductName;

	/**refers to prodcut size displayed on confirm section*/
	@FindBy(xpath = "//table[@id='cart_summary']//p[@class='product-name']/following-sibling::small/a")
	private WebElement txtConfirmOrderProductSize;

	/**
	 * This method checks if checkout page is displayed
	 * 
	 * @return boolean true if checkout page is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isCheckoutPageDisplayed() throws AutomationException {
		log.info("isCheckoutPageDisplayed() is invoked and boolean will be returned");
		return (BrowserUtils.isDisplayed(tblCartContents) & BrowserUtils.isDisplayed(txtCartTitle));
	}

	/**
	 * This method clicks on proceed to checkout button displayed on different steps
	 * in the check out process except Shipping page. For shipping page,
	 * 
	 * @see #proceedToCheckoutOnShipping()
	 * @author Mitulsinh Vaghela
	 */
	public void proceedToCheckout() throws AutomationException {
		log.info("proceedToCheckout() is invoked");
		BrowserUtils.click(btnProceedToCheckout);
		log.info("proceedToCheckout() is completed");
	}

	/**
	 * This method clicks on proceed to checkout button displayed on shipping page
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void proceedToCheckoutOnShipping() throws AutomationException {
		log.info("proceedToCheckoutOnShipping() is invoked");
		BrowserUtils.click(btnProceedToCheckoutOnShipping);
		log.info("proceedToCheckoutOnShipping() is completed");
	}

	/**
	 * This method checks if sign in section is displayed in the check out process
	 * 
	 * @return boolean true if sign in section is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isSigninPageDisplayed() throws AutomationException {
		log.info("isSigninPageDisplayed() is invoked and boolean will be returned");
		return BrowserUtils.isDisplayed(btnCreateAccount);
	}

	/**
	 * This method sets value in Email Address textbox displayed on sign in section
	 * 
	 * @param email email address
	 * @author Mitulsinh Vaghela
	 */
	public void setEmailAddress(String email) throws AutomationException {
		log.info("setEmailAddress(String email) is invoked");
		BrowserUtils.write(tbxEmail, email);
		log.info("setEmailAddress(String email) is completed");
	}

	/**
	 * This method clicks on Create Account button displayed on sign in section
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void createAnAccount() throws AutomationException {
		log.info("createAnAccount() is invoked");
		BrowserUtils.click(btnCreateAccount);
		log.info("createAnAccount() is completed");
	}

	/**
	 * This method sets value in First Name textbox on register user form
	 * 
	 * @param firstName first name
	 * @author Mitulsinh Vaghela
	 */
	public void setFirstName(String firstName) throws AutomationException {
		log.info("setFirstName(String firstName) is invoked");
		BrowserUtils.write(tbxFirstName, firstName);
		log.info("setFirstName(String firstName) is completed");

	}

	/**
	 * This method sets value in Last Name textbox on register user form
	 * 
	 * @param LastName last name
	 * @author Mitulsinh Vaghela
	 */
	public void setLastName(String LastName) throws AutomationException {
		log.info("setLastName(String LastName) is invoked");
		BrowserUtils.write(tbxLastName, LastName);
		log.info("setLastName(String LastName) is completed");
	}

	/**
	 * This method sets value in password textbox on register user form
	 * 
	 * @param password password
	 * @author Mitulsinh Vaghela
	 */
	public void setPassword(String password) throws AutomationException {
		log.info("setPassword(String password) is invoked");
		BrowserUtils.write(tbxPassword, password);
		log.info("setPassword(String password) is completed");
	}

	/**
	 * This method selects a value in Birth Day dropdown on register user form
	 * 
	 * @param day Birth day - 1,2,3...31
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthDay(String day) throws AutomationException {
		log.info("selectBirthDay(String day) is invoked");
		BrowserUtils.selectValueByValue(drpDayofBirth, day);
		log.info("selectBirthDay(String day) is completed");
	}

	/**
	 * This method selects a value in Month dropdown on register user form
	 * 
	 * @param month Month - January, February so on.
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthMonth(String Month) throws AutomationException {
		log.info("selectBirthDay(String day) is invoked");
		BrowserUtils.selectValueByValue(drpMonthofBirth, Month);
		log.info("selectBirthDay(String day) is completed ");

	}

	/**
	 * This method selects a value in year dropdown on register user form
	 * 
	 * @param year Year - 1990,2000 and so on.
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthYear(String Year) throws AutomationException {
		log.info("selectBirthYear(String Year) is invoked");
		BrowserUtils.selectValueByValue(drpYearsofBirth, Year);
		log.info("selectBirthYear(String Year) is completed");

	}

	/**
	 * This method sets address value in the Address1 textbox on register user form
	 * 
	 * @param addressLine1 address string
	 * @author Mitulsinh Vaghela
	 */
	public void setAddressLine1(String addressLine1) throws AutomationException {
		log.info("setAddressLine1(String addressLine1) is invoked");
		BrowserUtils.write(tbxAddress1, addressLine1);
		log.info("setAddressLine1(String addressLine1) is completed");

	}

	/**
	 * This method sets city value in city textbox on register user form
	 * 
	 * @param city city string
	 * @author Mitulsinh Vaghela
	 */
	public void setCity(String city) throws AutomationException {
		log.info("setCity(String city) is invoked");
		BrowserUtils.write(tbxCity, city);
		log.info("setCity(String city) is completed");

	}

	/**
	 * This method selects a value in year State dropdown on register user form
	 * 
	 * @param state name of a US state e.g. Newyork
	 * @author Mitulsinh Vaghela
	 */
	public void selectState(String state) throws AutomationException {
		log.info("selectState(String state) is invoked");
		BrowserUtils.selectValueByName(drpState, state);
		log.info("selectState(String state) is completed");
	}

	/**
	 * This method sets postal code value in Zip/Postal code textbox on register
	 * user form
	 * 
	 * @param postalCode five digital US postal code
	 * @author Mitulsinh Vaghela
	 */
	public void setPostalCode(String postalCode) throws AutomationException {
		log.info("setPostalCode(String postalCode) is invoked");
		BrowserUtils.write(tbxPostalCode, postalCode);
		log.info("setPostalCode(String postalCode) is completed");

	}

	/**
	 * This method selects a value in year Country dropdown on register user form
	 * 
	 * @param country name of the country i.e. United States
	 * @author Mitulsinh Vaghela
	 */
	public void selectCountry(String country) throws AutomationException {
		log.info("selectCountry(String country) is invoked");
		BrowserUtils.selectValueByName(drpCountry, country);
		log.info("selectCountry(String country) is completed");

	}

	/**
	 * This method sets mobile phone number in Mobile phone textbox on register user
	 * form
	 * 
	 * @param phoneNumber 10 digital phone number
	 * @author Mitulsinh Vaghela
	 */
	public void setMobilePhone(String phoneNumber) throws AutomationException {
		log.info("setMobilePhone(String phoneNumber) is invoked");
		BrowserUtils.write(tbxMobilePhoneNumber, phoneNumber);
		log.info("setMobilePhone(String phoneNumber) is completed");

	}

	/**
	 * This method sets value in the Address Alias textbox on register user form
	 * 
	 * @param addressAlia Address Alias string
	 * @author Mitulsinh Vaghela
	 */
	public void setAddressAlias(String addressAlias) throws AutomationException {
		log.info("setMobilePhone(String phoneNumber) is invoked");
		BrowserUtils.write(tbxAddressAlias, addressAlias);
		log.info("setMobilePhone(String phoneNumber) is completed");

	}

	/**
	 * This method clicks on the register button on register user form. If user had
	 * provided valid values for all the fields, this will result in successfully
	 * creating a new user
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void register() throws AutomationException {
		log.info("register() is invoked");
		BrowserUtils.click(btnRegister);
		log.info("register() is completed");
	}

	/**
	 * This method checks if use same address for shipping and billing address
	 * checkbox is displayed on Shipping page. This helps in validating if we have
	 * successfully reached the shipping page.
	 * 
	 * @return boolean returns true if "use same address" option is displayed else
	 *         false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isUseSameAddressOptionDisplayed() throws AutomationException {
		log.info("isUseSameAddressOptionDisplayed() is invoked and boolean will be returned");
		return BrowserUtils.isDisplayed(cbxUseSameAddress);
	}

	/**
	 * This method clicks on the "agree to terms and service" checkbox on shipping
	 * page.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void agreeToTermsAndService() throws AutomationException {
		log.info("agreeToTermsAndService() is invoked");
		cbxTermsOfService.click();
		log.info("agreeToTermsAndService() is completed");
		// Helper.click( cbxTermsOfService);

	}

	/**
	 * This method fetches the value of product name displayed on the confirm order
	 * screen and returns it as a string
	 * 
	 * @return String name of product name displayed on confirm order screen
	 * @author Mitulsinh Vaghela
	 */
	public String getProductNameOnConfirmOrderScreen() throws AutomationException {
		log.info("agreeToTermsAndService() is invoked and string will be returned");
		return lnkConfirmOrderProductName.getText();
	}

	/**
	 * This method fetches the value of product size displayed on the confirm order
	 * screen and returns it as a string
	 * 
	 * @return String name of product size displayed on confirm order screen
	 * @author Mitulsinh Vaghela
	 */
	public String getProductSizeOnConfirmOrderScreen() throws AutomationException {
		log.info("getProductSizeOnConfirmOrderScreen() is invoked and string will be returned");
		String[] productDetails = txtConfirmOrderProductSize.getText().split(",");
		return productDetails[1].split(":")[1];
	}
}
