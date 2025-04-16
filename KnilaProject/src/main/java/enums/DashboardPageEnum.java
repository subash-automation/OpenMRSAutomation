package enums;

import org.openqa.selenium.By;

public enum DashboardPageEnum {

    REGISTER_PATIENT(By.xpath("//a[contains(@id,'registerPatient')]"), "Register a patient button"),
    ALL_APPS(By.id("apps"), "All apps");

    private By locator;
    private String xPath;
    private String desc;

    DashboardPageEnum (By locator, String desc){
        this.locator = locator;
        this.desc = desc;
    }

    DashboardPageEnum(String xPath, String desc){
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
