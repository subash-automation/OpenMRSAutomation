package enums;

import org.openqa.selenium.By;

public enum VisitsPageEnum {

    YES_ENDVISIT(By.xpath("//h3[text()='End Visit']//parent::div//following-sibling::div//button[text()='Yes' and not(@type='submit')]"),"Yes button"),
    SAVE_BTN(By.xpath("//button[contains(text(),'Save')]"), "Save button"),
    SAVE_FORM(By.xpath("//a[@id='save-form']"),"Save form"),
    CONFIRM_TAB(By.xpath("//ul[@id='formBreadcrumb']//li//span[text()='Confirm']"), "Confirm tab"),
    WEIGHT_TAB(By.xpath("//ul[@id='formBreadcrumb']//li//span[text()='Weight (kg)']"), "Confirm tab"),
    BMI_TAB(By.xpath("//ul[@id='formBreadcrumb']//li//span[text()='(Calculated) BMI']"), "Confirm tab"),
    BMI_FROM_UI(By.xpath("//span[@id='calculated-bmi']"),"Calculated BMI"),
    NEXT(By.id("next-button"), "Next button"),
    WEIGHT_INPUT(By.xpath("//span[@id='weight']//input[contains(@class,'focused')]"), "Weight input"),
    HEIGHT_INPUT(By.xpath("//span[@id='height']//input[contains(@class,'focused')]"),"Height input"),
    PATIENT_NAME_LINK("//a[contains(text(),'%s')]","Patient name link"),
    TOASTER_SUCCESS(By.xpath("//div[@class='toast-item toast-type-success']//p[text()='The attachment was successfully uploaded.']"),"Toaster success message"),
    UPLOAD_BTN_DISABLE(By.xpath("//button[text()='Upload file' and @disabled='disabled']"), "Upload button enabled"),
    UPLOAD_BTN_ENABLE(By.xpath("//button[text()='Upload file' and not(@disabled='disabled')]"), "Upload button enabled"),
    CAPTION(By.xpath("//textarea[@placeholder='Enter a caption']"), "Caption input"),
    DROP_FILE(By.id("visit-documents-dropzone"), "File drop section"),
    ATTACHMENTS(By.xpath("//a[contains(@id,'attachments')]"), "Attachments"),
    CAPTURE_VITALS(By.xpath("//a[contains(@id,'vitals')]"), "Capture vitals"),
    END_VISIT(By.xpath("//i[@class='icon-off']//parent::a"), "End Visit"),
    VISITS_LIST(By.xpath("//ul[@id='visits-list']"), "Visits list");

    private By locator;
    private String xPath;
    private String desc;

    VisitsPageEnum(By locator, String desc){
        this.locator = locator;
        this.desc = desc;
    }

    VisitsPageEnum(String xPath, String desc){
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
