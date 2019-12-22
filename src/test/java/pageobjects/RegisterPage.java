package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
	
	@FindBy(id="id_gender1")
	WebElement titleMr;
	
	@FindBy(id="id_gender2")
	WebElement titleMrs;
	
	@FindBy(id="customer_firstname")
	WebElement firstName;
	
	@FindBy(id="customer_lastname")
	WebElement lastName;
	
	@FindBy(id="passwd")
	WebElement Password;
	
	@FindBy(id="days")
	WebElement dayofBirth;
	
	@FindBy(id="months")
	WebElement monthofBirth;

	@FindBy(id="years")
	WebElement yearsofBirth;
	
	@FindBy(id="firstname")
	WebElement addressFirstName;

	@FindBy(id="lastname")
	WebElement addressLastName;

	@FindBy(id="address1")
	WebElement address;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="id_state")
	WebElement state;
	
	@FindBy(id="postcode")
	WebElement postalCode;
	
	@FindBy(id="id_country")
	WebElement country;
	
	@FindBy(id="phone_mobile")
	WebElement mobilePhoneNumber;
	
	@FindBy(id="submitAccount")
	WebElement Register;
	
	
	
	

}
