package enums;

import org.openqa.selenium.By;

public enum LoginPageEnum {

    LOGIN_BTN(By.id("loginButton"), "Login button"),
    USERNAME(By.id("username"), "Username input"),
    PASSWORD(By.id("password"), "Password input"),
    LOCATION("//ul[@id='sessionLocation']//li[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '%s']", "Location"),
    LOGIN_FORM(By.xpath("//form[@id='login-form']"), "Login page");

    private By locator;
    private String xPath;
    private String desc;

    LoginPageEnum (By locator, String desc){
        this.locator = locator;
        this.desc = desc;
    }

    LoginPageEnum(String xPath, String desc){
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
