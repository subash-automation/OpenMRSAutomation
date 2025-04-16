package page;

import entity.PatientDemographic;
import enums.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.WaitUtil;

import static core.DriverManager.getDriver;

public class VisitsPage extends AbstractBasePage {

    @Override
    public void isPageLoaded() {
        System.out.println("Ensure Visits page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), VisitsPageEnum.VISITS_LIST.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form!=null && form.isDisplayed();
        System.out.println("Is visits page loaded: "+result);
        Assert.assertTrue(result, "Visits page is not loaded");
    }

    /**
     * Click on the given element
     * @param path
     */
    public void clickOnElement(VisitsPageEnum path){
        System.out.println("Click on "+path.getDesc()+" element");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Click on the given element
     * @param path
     */
    public void clickOnElement(VisitsPageEnum path, String value){
        WebElement element = WaitUtil.waitForVisibility(getDriver(), By.xpath(String.format(path.getXpath(), value)), Timeout.FIVE_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    public void isAttachmentPageLoaded() {
        System.out.println("Ensure attachment page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), VisitsPageEnum.DROP_FILE.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form!=null && form.isDisplayed();
        System.out.println("Is attchment page loaded: "+result);
        Assert.assertTrue(result, "Attachment page is not loaded");
    }

    /**
     * Fill the value in the given field
     * @param path
     * @param text
     */
    public void fillFields(VisitsPageEnum path, String text, boolean mandy){
        if(mandy){
            System.out.println("Enter value :" + text + " in mandatory field " + path.getDesc());
            Assert.assertTrue(text!=null && !text.isEmpty(), "Entering value is null or empty for a mandatory field");
        }
        System.out.println("Enter value :" + text + " in field " + path.getDesc());
        WebElement field = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(field, path.getDesc()+" field is not displayed");
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    /**
     * Check and click the upload button
     */
    public void checkAndClickUploadBtn(){
        System.out.println("Check the Upload button is enabled and click if enabled");
        WebElement enableBtn = WaitUtil.waitForVisibility(getDriver(), VisitsPageEnum.UPLOAD_BTN_ENABLE.getLocator(), Timeout.TEN_SEC);
        if(enableBtn==null || !enableBtn.isDisplayed()){
            WebElement disableBtn = WaitUtil.waitForVisibility(getDriver(), VisitsPageEnum.UPLOAD_BTN_DISABLE.getLocator(), Timeout.TEN_SEC);
            System.out.println(disableBtn!=null && disableBtn.isDisplayed() ? "Upload button is diabled so unable to click":"Upload button is not diaplyed");
        } else {
            clickOnElement(VisitsPageEnum.UPLOAD_BTN_ENABLE);
            System.out.println("Upload button clicked!");
        }
    }

    /**
     * Check the element displayed
     * @param path
     * @return
     */
    public boolean isElementDisplayed(VisitsPageEnum path) {
        System.out.println("Check the"+path.getDesc()+" is loaded or not");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        boolean result = element!=null && element.isDisplayed();
        System.out.println("Is "+path.getDesc()+" displayed: "+result);
        return result;
    }

    /**
     * Go back to Patient details page
     * @return
     */
    public PatientDetailsPage goBackToPatientDetailPage(PatientDemographic basic){
        String patientName;
        if(basic.getMiddleName()!=null)
            patientName = basic.getGivenName()+" "+basic.getMiddleName()+" "+basic.getFamilyName();
        else
            patientName = basic.getGivenName()+" "+basic.getFamilyName();
        System.out.println("Go back to patient details page by clicking the patient name link");
        WebElement name = WaitUtil.waitForVisibility(getDriver(), By.xpath(String.format(VisitsPageEnum.PATIENT_NAME_LINK.getXpath(), patientName)), Timeout.TEN_SEC);
        Assert.assertTrue(name!=null && name.isDisplayed(), "Patient name link was not displayed");
        clickOnElement(VisitsPageEnum.PATIENT_NAME_LINK, patientName);
         return PageFactory.initElements(getDriver(), PatientDetailsPage.class);
    }

    /**
     * get text from the field
     * @param path
     * @return
     */
    public String getTextFromField(VisitsPageEnum path){
        System.out.println("Get text from "+path.getDesc()+" field");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        System.out.println("Text from element is: "+element.getText().trim());
        return element.getText().trim();
    }

    /**
     * End visit
     * @return
     */
    public PatientDetailsPage endVisit(){
        System.out.println("Click End visit and go to Patient details page");
        WebElement endVisit = WaitUtil.waitForVisibility(getDriver(), VisitsPageEnum.END_VISIT.getLocator(), Timeout.TEN_SEC);
        clickOnElement(VisitsPageEnum.END_VISIT);
        clickOnElement(VisitsPageEnum.YES_ENDVISIT);
        return PageFactory.initElements(getDriver(), PatientDetailsPage.class);
    }
}
