package enums;

import org.openqa.selenium.By;

public enum MergePageEnum {

    RETURN(By.xpath("//input[@value='Return']"), "Return button"),

    MERGE_BTN_ENABLED(By.xpath("//input[@id='mergeVisitsBtn' and @class='confirm enabled']"),"Enabled Merge button"),

    CHECKBOX("//td[contains(text(),'%s')]//preceding-sibling::td[1][contains(text(),'%s')]//preceding-sibling::td//input[@class='selectVisit']", "Checkbox with respect to ennDate and encounter"),

    TABLE(By.xpath("//h3[text()='Merge Visits']//following-sibling::form"), "Visits table");

    private By locator;
    private String xPath;
    private String desc;

    MergePageEnum(By locator, String desc){
        this.locator = locator;
        this.desc = desc;
    }

    MergePageEnum(String xPath, String desc){
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
