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

	private static final Logger log = Logger.getLogger(CheckoutPage.class);
	/**
	 * This constructor instantiates the checkout page object and initializes the
	 * page factory objects.
	 * 
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

	@FindBy(id = "addressesAreEquals")
	WebElement cbxUseSameAddress;

	@FindBy(id = "cgv")
	WebElement cbxTermsOfService;

	@FindBy(css = "table#cart_summary p.product-name a")
	WebElement lnkConfirmOrderProductName;

	@FindBy(xpath = "//table[@id='cart_summary']//p[@class='product-name']/following-sibling::small/a")
	WebElement txtConfirmOrderProductSize;

	/**
	 * This method checks if checkout page is displayed
	 * 
	 * @return boolean true if checkout page is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isCheckoutPageDisplayed() throws AutomationException {
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
		BrowserUtils.click(btnProceedToCheckout);
	}

	/**
	 * This method clicks on proceed to checkout button displayed on shipping page
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void proceedToCheckoutOnShipping() throws AutomationException {
		BrowserUtils.click(btnProceedToCheckoutOnShipping);
	}

	/**
	 * This method checks if sign in section is displayed in the check out process
	 * 
	 * @return boolean true if sign in section is displayed else false
	 * @author Mitulsinh Vaghela
	 */
	public boolean isSigninPageDisplayed() throws AutomationException {
		return BrowserUtils.isDisplayed(btnCreateAccount);
	}

	/**
	 * This method sets value in Email Address textbox displayed on sign in section
	 * 
	 * @param email email address
	 * @author Mitulsinh Vaghela
	 */
	public void setEmailAddress(String email) throws AutomationException {
		BrowserUtils.write(tbxEmail, email);
	}

	/**
	 * This method clicks on Create Account button displayed on sign in section
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void createAnAccount() throws AutomationException {
		BrowserUtils.click(btnCreateAccount);
	}

	/**
	 * This method sets value in First Name textbox on register user form
	 * 
	 * @param firstName first name
	 * @author Mitulsinh Vaghela
	 */
	public void setFirstName(String firstName) throws AutomationException {
		BrowserUtils.write(tbxFirstName, firstName);

	}

	/**
	 * This method sets value in Last Name textbox on register user form
	 * 
	 * @param LastName last name
	 * @author Mitulsinh Vaghela
	 */
	public void setLastName(String LastName) throws AutomationException {
		BrowserUtils.write(tbxLastName, LastName);
	}

	/**
	 * This method sets value in password textbox on register user form
	 * 
	 * @param password password
	 * @author Mitulsinh Vaghela
	 */
	public void setPassword(String password) throws AutomationException {
		BrowserUtils.write(tbxPassword, password);
	}

	/**
	 * This method selects a value in Birth Day dropdown on register user form
	 * 
	 * @param day Birth day - 1,2,3...31
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthDay(String day) throws AutomationException {
		BrowserUtils.selectValueByValue(drpDayofBirth, day);
	}

	/**
	 * This method selects a value in Month dropdown on register user form
	 * 
	 * @param month Month - January, February so on.
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthMonth(String Month) throws AutomationException {
		BrowserUtils.selectValueByValue(drpMonthofBirth, Month);

	}

	/**
	 * This method selects a value in year dropdown on register user form
	 * 
	 * @param year Year - 1990,2000 and so on.
	 * @author Mitulsinh Vaghela
	 */
	public void selectBirthYear(String Year) throws AutomationException {
		BrowserUtils.selectValueByValue(drpYearsofBirth, Year);

	}

	/**
	 * This method sets address value in the Address1 textbox on register user form
	 * 
	 * @param addressLine1 address string
	 * @author Mitulsinh Vaghela
	 */
	public void setAddressLine1(String addressLine1) throws AutomationException {
		BrowserUtils.write(tbxAddress1, addressLine1);

	}

	/**
	 * This method sets city value in city textbox on register user form
	 * 
	 * @param city city string
	 * @author Mitulsinh Vaghela
	 */
	public void setCity(String city) throws AutomationException {
		BrowserUtils.write(tbxCity, city);

	}

	/**
	 * This method selects a value in year State dropdown on register user form
	 * 
	 * @param state name of a US state e.g. Newyork
	 * @author Mitulsinh Vaghela
	 */
	public void selectState(String state) throws AutomationException {
		BrowserUtils.selectValueByName(drpState, state);
	}

	/**
	 * This method sets postal code value in Zip/Postal code textbox on register
	 * user form
	 * 
	 * @param postalCode five digital US postal code
	 * @author Mitulsinh Vaghela
	 */
	public void setPostalCode(String postalCode) throws AutomationException {
		BrowserUtils.write(tbxPostalCode, postalCode);

	}

	/**
	 * This method selects a value in year Country dropdown on register user form
	 * 
	 * @param country name of the country i.e. United States
	 * @author Mitulsinh Vaghela
	 */
	public void selectCountry(String country) throws AutomationException {
		BrowserUtils.selectValueByName(drpCountry, country);

	}

	/**
	 * This method sets mobile phone number in Mobile phone textbox on register user
	 * form
	 * 
	 * @param phoneNumber 10 digital phone number
	 * @author Mitulsinh Vaghela
	 */
	public void setMobilePhone(String phoneNumber) throws AutomationException {
		BrowserUtils.write(tbxMobilePhoneNumber, phoneNumber);

	}

	/**
	 * This method sets value in the Address Alias textbox on register user form
	 * 
	 * @param addressAlia Address Alias string
	 * @author Mitulsinh Vaghela
	 */
	public void setAddressAlias(String addressAlias) throws AutomationException {
		BrowserUtils.write(tbxAddressAlias, addressAlias);

	}

	/**
	 * This method clicks on the register button on register user form. If user had
	 * provided valid values for all the fields, this will result in successfully
	 * creating a new user
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void register() throws AutomationException {
		BrowserUtils.click(btnRegister);
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
		return BrowserUtils.isDisplayed(cbxUseSameAddress);
	}

	/**
	 * This method clicks on the "agree to terms and service" checkbox on shipping
	 * page.
	 * 
	 * @author Mitulsinh Vaghela
	 */
	public void agreeToTermsAndService() throws AutomationException {
		cbxTermsOfService.click();
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
		String[] productDetails = txtConfirmOrderProductSize.getText().split(",");
		return productDetails[1].split(":")[1];
	}
}
