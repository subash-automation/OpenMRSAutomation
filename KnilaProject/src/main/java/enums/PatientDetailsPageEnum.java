package enums;

import org.openqa.selenium.By;

public enum PatientDetailsPageEnum {

    DELETED_MESSAGE(By.xpath("//div[@class='toast-item toast-type-success']//p[text()='Patient has been deleted successfully']"), "Delete success message"),

    DELETE_CONFIRM(By.xpath("//input[@id='delete-reason']//following-sibling::button[@class='confirm right']"),"Delete confirm button"),

    REASON_INPUT(By.xpath("//input[@id='delete-reason']"), "Reason input"),

    DELETE_DIALOG_BOX(By.xpath("//div[@class='dialog-content']//p[contains(text(),'Are you sure you want to DELETE the patient')]"), "Delete dialog box"),

    FUTURE_DATE_IN_DATEPICKER("//div[@class='datetimepicker-days']//table//tbody//td[@class='day disabled' and text()='%s']", "Future date in datepicker"),

    PAST_START_DATE(By.xpath("//span[@id='retrospectiveVisitStartDate']"),"Past start date input"),

    MERGE_ENTRY("//visitbyencountertype//a[contains(text(),'%s')]//following-sibling::div[text()='%s']", "Merged visits entry"),

    PATIENT_NAME_LINK("//a[contains(text(),'%s')]","Patient name link"),

    CONFIRM_BMI(By.xpath("//h3[text()='(Calculated) BMI']//following-sibling::span//span[@id='calculated-bmi']"), "BMI in patient page"),

    CONFIRM_WEIGHT(By.xpath("//h3[text()='Weight (kg)']//following-sibling::p//span[@class='value']"), "Weight in patient page"),

    CONFIRM_HEIGHT(By.xpath("//h3[text()='Height (cm)']//following-sibling::p//span[@class='value']"), "Height in patient page"),

    YES_ENDVISIT(By.xpath("//button[text()='Yes' and not(@type='submit')]"),"Yes button"),

    ATTACHMENT_UPLOAD(By.xpath("//div[text()='Attachment Upload']"), "Attachment upload tag"),

    VITALS_TAG(By.xpath("//div[text()='Vitals']"), "Attachment upload tag"),

    VISIT_ENTRIES(By.xpath("//h3[text()='RECENT VISITS']/../../div//following-sibling::div[@class='info-body']//a"),"Visit entries"),

    ATTACHMENTS_THUMBNAIL(By.xpath("//div[@class='att_thumbnail-container ng-scope']"), "Attachment thumbnails"),

    CONFIRM(By.id("start-visit-with-visittype-confirm"), "Confirm button"),

    START_VISIT(By.xpath("//h3[text()='General Actions']//following-sibling::li//a//div//div[contains(text(),'Start Visit')]"), "Start visit button"),

    MERGE_VISITS(By.xpath("//h3[text()='General Actions']//following-sibling::li//a//div//div[contains(text(),'Merge Visits')]"), "Merge visits button"),

    ADD_PAST_VISIT(By.xpath("//h3[text()='General Actions']//following-sibling::li//a//div//div[contains(text(),'Add Past Visit')]"), "Add past visit button"),

    END_VISIT(By.xpath("//div[@class='col-12 col-lg-3 p-0']//h3[text()='Current Visit Actions']//following-sibling::li//a//div//div[contains(text(),'End Visit')]"), "End visit button"),

    DELETE_PATIENT(By.xpath("//h3[text()='General Actions']//following-sibling::li//a//div//div[contains(text(),'Delete Patient')]"), "End visit button"),

    AGE(By.xpath("//div[@class='gender-age col-auto']//span[2]"), "Age field"),

    GENERAL_ACTIONS(By.xpath("//h3[text()='General Actions']/../../ul[@class='float-left']"), "General actions panel");


    private By locator;
    private String xPath;
    private String desc;

    PatientDetailsPageEnum(By locator, String desc){
        this.locator = locator;
        this.desc = desc;
    }

    PatientDetailsPageEnum(String xPath, String desc){
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
