package enums;

import org.openqa.selenium.By;

public enum RegisterPatientPageEnum {

    CONFIRM_BTN(By.xpath("//input[@id='submit']"),"Confirm button"),

    CONFIRM_PHONE(By.xpath("//span[contains(text(),'Phone Number:')]/parent::p"),"Phone number in confirm page"),

    CONFIRM_ADDRESS(By.xpath("//span[contains(text(),'Address:')]/parent::p"),"Address in confirm page"),

    CONFIRM_BIRTHDATE(By.xpath("//span[contains(text(),'Birthdate:')]/parent::p"),"Birthdate in confirm page"),

    CONFIRM_GENDER(By.xpath("//span[contains(text(),'Gender:')]/parent::p"),"Gender in confirm page"),

    CONFIRM_NAME(By.xpath("//span[contains(text(),'Name:')]/parent::p"),"Name in confirm page"),

    PHONE(By.xpath("//input[@name='phoneNumber']"), "Phone number input"),

    POST_CODE(By.xpath("//input[@name='postalCode']"), "Postal code input"),

    COUNTRY(By.xpath("//input[@name='country']"), "Country input"),

    STATE(By.xpath("//input[@name='stateProvince']"), "State input"),

    CITY(By.xpath("//input[@name='cityVillage']"), "City input"),

    ADDRESS2(By.xpath("//input[@name='address2']"), "Address line2 input"),

    ADDRESS1(By.xpath("//input[@name='address1']"), "Address line1 input"),

    BIRTH_YEAR(By.id("birthdateYear-field"), "Yaer of Birth"),

    BIRTH_MONTH(By.id("birthdateMonth-field"), "Month dropdown"),

    BIRTH_DATE(By.id("birthdateDay-field"), "Day of Birth"),

    GENDER_DD(By.id("gender-field"), "Gender"),

    PREVIOUS(By.id("prev-button"), "Previous button"),

    NEXT(By.id("next-button"), "Next button"),

    FAMILY(By.xpath("//input[@name='familyName']"), "Family name input"),

    MIDDLE(By.xpath("//input[@name='middleName']"), "Middle name input"),

    GIVEN(By.xpath("//input[@name='givenName']"), "Given name input"),

    PATIENT_FORM(By.xpath("//h2[contains(text(),'Register a patient')]//parent::div[@id='content']"), "patient form");


    private By locator;
    private String xPath;
    private String desc;

    RegisterPatientPageEnum(By locator, String desc){
        this.locator = locator;
        this.desc = desc;
    }

    RegisterPatientPageEnum(String xPath, String desc){
        this.xPath = xPath;
        this.desc = desc;
    }

    public By getLocator(){
        return locator;
    }

    public String getXpath(){
        return xPath;
    }

    public String getDesc(){
        return desc;
    }
}
