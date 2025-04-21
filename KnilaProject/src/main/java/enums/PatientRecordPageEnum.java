package enums;

import org.openqa.selenium.By;

public enum PatientRecordPageEnum {

    SEARCH_RESULT_ROW("//td[5][contains(text(),'%s')]//preceding-sibling::td[3][text()='%s']//ancestor::tr", "Row of search result"),

    SEARCH_FIELD(By.xpath("//input[@id='patient-search']"),"Search field"),

    CONTENT(By.id("content"), "Page content");

    private By locator;
    private String xPath;
    private String desc;

    PatientRecordPageEnum(By locator, String desc){
        this.locator = locator;
        this.desc = desc;
    }

    PatientRecordPageEnum(String xPath, String desc){
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
