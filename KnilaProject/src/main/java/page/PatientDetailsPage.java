package page;

import businessFlow.VisitsBL;
import entity.PatientDemographic;
import entity.Visits;
import entity.Vitals;
import enums.LoginPageEnum;
import enums.PatientDetailsPageEnum;
import enums.Timeout;
import enums.VisitsPageEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.DateUtil;
import utility.WaitUtil;

import java.util.List;

import static core.DriverManager.getDriver;

public class PatientDetailsPage extends AbstractBasePage{

    @Override
    public void isPageLoaded() {
        System.out.println("Ensure patient details page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.GENERAL_ACTIONS.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form!=null && form.isDisplayed();
        System.out.println("Is patient details page loaded: "+result);
        Assert.assertTrue(result, "Details page is not loaded");
    }

    /**
     * get text from the field
     * @return
     */
    public String getAgeFromUI(){
        System.out.println("Get Age from UI");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.AGE.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, "Age was not diaplayed");
        String fullText = element.getText().trim();
        String age = fullText.split(" ")[0];
        System.out.println("Age from UI is: "+age);
        return age;
    }

    /**
     * Click on the given element
     */
    public void clickOnConfirm(){
        System.out.println("Click on confirm button");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.CONFIRM.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, "Confirm button was not displayed");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Click on the given element
     */
    public VisitsPage startVisit(){
        System.out.println("Click on start visit element");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.START_VISIT.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, "Start visit button was not displayed");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
        clickOnConfirm();
        return PageFactory.initElements(getDriver(), VisitsPage.class);
    }

    /**
     * Check the element displayed
     * @param path
     * @return
     */
    public boolean isElementDisplayed(PatientDetailsPageEnum path) {
        System.out.println("Check the"+path.getDesc()+" is loaded or not");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        boolean result = element!=null && element.isDisplayed();
        System.out.println("Is "+path.getDesc()+" displayed: "+result);
        return result;
    }

    /**
     * Verify the attchment traces (Visit entry date and upload tag)
     * @param entryDate
     */
    public void verifyTheAttachmentUploadTraces(String entryDate, int totalVsisits){
        System.out.println("Verify the visit entry on "+entryDate+" and the attachment upload tag were diaplyed");
        List<WebElement> entries = WaitUtil.waitForVisibilityOfAll(getDriver(), PatientDetailsPageEnum.VISIT_ENTRIES.getLocator(), Timeout.TEN_SEC);
        Assert.assertTrue(entries.size()==totalVsisits, "There is a mismatch in no of entries. Actual entries: "+entries.size());
        Assert.assertTrue(entryDate.equalsIgnoreCase(entries.get(0).getText().trim()),"Entry date is not displayed as expected. Displayed date: "+entries.get(0).getText().trim());
        System.out.println("Visit entry is displayed with date: "+entryDate);
        WebElement tag = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.ATTACHMENT_UPLOAD.getLocator(), Timeout.FIVE_SEC);
        Assert.assertTrue(tag!=null && tag.isDisplayed(), "Attachemnt upload tag is not displayed");
        System.out.println("Attachment upload tag displayed in recent visit");
    }

    /**
     * Verify the attchment traces (Visit entry date and upload tag)
     * @param entryDate
     */
    public void verifyRecentVisitMerge(String entryDate, List<Visits> visitsList) {
        System.out.println("Verify the visit merge on " + entryDate + " is diaplyed");
        String encounters = visitsList.get(0).getEncounter().trim();
        for (int i = 1; i < visitsList.size(); i++) {
            encounters = encounters + ", " + visitsList.get(i).getEncounter().trim();
        }
        String xPath = String.format(PatientDetailsPageEnum.MERGE_ENTRY.getXpath(), entryDate.trim(), encounters);
        WebElement mergeEntry = WaitUtil.waitForVisibility(getDriver(), By.xpath(xPath), Timeout.TEN_SEC);
        Assert.assertTrue(mergeEntry != null && mergeEntry.isDisplayed(), "Recent merge entry was not displayed");
    }

    /**
     * Click on the given element
     * @param path
     */
    public void clickOnElement(PatientDetailsPageEnum path){
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
    public void clickOnElement(PatientDetailsPageEnum path, String value){
        WebElement element = WaitUtil.waitForVisibility(getDriver(), By.xpath(String.format(path.getXpath(), value)), Timeout.FIVE_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * End visit
     */
    public void endVsit(){
        System.out.println("Click on End Visit button");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.END_VISIT.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, "End vist element is NULL");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }

        System.out.println("Click Yes on pop up");
        WebElement yes = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.YES_ENDVISIT.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(yes, "End vist element is NULL");
        try{
            yes.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", yes);
        }
        WaitUtil.waitUntil(getDriver(),5);
    }

    /**
     * get text from the field
     * @param path
     * @return
     */
    public String getTextFromField(PatientDetailsPageEnum path){
        System.out.println("Get text from "+path.getDesc()+" field");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        System.out.println("Text from element is: "+element.getText().trim());
        return element.getText().trim();
    }

    public void ensureBMIDetails(Vitals vitals){
        System.out.println("Ensure Vitals and BMI populated correctly in patient details page");
        String height = getTextFromField(PatientDetailsPageEnum.CONFIRM_HEIGHT);
        Assert.assertTrue(Double.parseDouble(height) == vitals.getHeight(), "Height is mismatching in patient page");
        String weight = getTextFromField(PatientDetailsPageEnum.CONFIRM_WEIGHT);
        Assert.assertTrue(Double.parseDouble(weight)==vitals.getWeight(), "Weight is mismatching in patient page");
        String bmi = getTextFromField(PatientDetailsPageEnum.CONFIRM_BMI);
        Assert.assertTrue(Double.parseDouble(bmi)== VisitsBL.getInstance().calulateBMI(vitals), "BMI generated in patient page is not correct");
        System.out.println("All data populated properly in patient details page");
    }

    /**
     * Verify the attchment traces (Visit entry date and upload tag)
     * @param entryDate
     */
    public void verifyTheVitalTraces(String entryDate, int totalVisits){
        System.out.println("Verify the visit entry on "+entryDate+" and the attachment upload tag were diaplyed");
        List<WebElement> entries = WaitUtil.waitForVisibilityOfAll(getDriver(), PatientDetailsPageEnum.VISIT_ENTRIES.getLocator(), Timeout.TEN_SEC);
        Assert.assertTrue(entries.size()==totalVisits, "There is a mismatch in no of entries. Actual entries: "+entries.size());
        Assert.assertTrue(entryDate.equalsIgnoreCase(entries.get(0).getText().trim()),"Entry date is not displayed as expected. Displayed date: "+entries.get(0).getText().trim());
        System.out.println("Visit entry is displayed with date: "+entryDate);
        WebElement tag = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.VITALS_TAG.getLocator(), Timeout.FIVE_SEC);
        Assert.assertTrue(tag!=null && tag.isDisplayed(), "Vitals tag is not displayed");
        System.out.println("Vitals tag displayed in recent visit");
    }

    /**
     * Go back to Patient details page
     * @return
     */
    public PatientDetailsPage goBackToPatientDetailPage(PatientDemographic basic) throws InterruptedException {
        String patientName;
        if(basic.getMiddleName()!=null)
            patientName = basic.getGivenName()+" "+basic.getMiddleName()+" "+basic.getFamilyName();
        else
            patientName = basic.getGivenName()+" "+basic.getFamilyName();
        System.out.println("Go back to patient details page by clicking the patient name link");
        WebElement name = WaitUtil.waitForClickability(getDriver(), By.xpath(String.format(PatientDetailsPageEnum.PATIENT_NAME_LINK.getXpath(), patientName)), Timeout.TEN_SEC);
        Assert.assertTrue(name!=null && name.isDisplayed(), "Patient name link was not displayed");
        clickOnElement(PatientDetailsPageEnum.PATIENT_NAME_LINK, patientName);
        return PageFactory.initElements(getDriver(), PatientDetailsPage.class);
    }

    /**
     * Click on the given element
     */
    public MergePage startMerge(){
        System.out.println("Click on Merge visits element");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.MERGE_VISITS.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, "Merge visit button was not displayed");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }

        return PageFactory.initElements(getDriver(), MergePage.class);
    }

    /**
     * End visit
     */
    public void clickAddPastVist() {
        System.out.println("Click on Add past visit button");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), PatientDetailsPageEnum.ADD_PAST_VISIT.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, "End vist element is NULL");
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Check future dates are clickable
     * @return
     */
    public void checkFutureDatesClickable(){
        System.out.println("Check future dates are clickable");
        String currentDate = DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE);
        int date = Integer.parseInt(currentDate.split("\\.")[0].trim());
        String xPath = null;
        WebElement dateElement;
        for(int i=0; i<3; i++){
            date = date+1;
            xPath =  String.format(PatientDetailsPageEnum.FUTURE_DATE_IN_DATEPICKER.getXpath(),Integer.toString(date));
            dateElement = WaitUtil.waitForVisibility(getDriver(), By.xpath(xPath), Timeout.TEN_SEC);
            System.out.println("Is date "+date+" displayed: "+dateElement==null && !dateElement.isDisplayed());
            Assert.assertTrue(dateElement!=null && dateElement.isDisplayed(), "Date "+date+" is clickable");
        }
        System.out.println("Future dates are not clickable");
    }

    /**
     * Fill the value in the given field
     * @param path
     * @param text
     */
    public void fillFields(PatientDetailsPageEnum path, String text, boolean mandy){
        if(mandy){
            System.out.println("Enter value :" + text + " in mandatory field " + path.getDesc());
            Assert.assertTrue(text!=null && !text.isEmpty(), "Entering value is null or emplty for a mandatory field");
        }
        System.out.println("Enter value :" + text + " in field " + path.getDesc());
        WebElement field = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.FIVE_SEC);
        Assert.assertNotNull(field, path.getDesc()+" field is not displayed");
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    /**
     * Delete patient
     * @param reason
     */
    public PatientRecordPage deletePatient(String reason){
        System.out.println("Delete the patient");
        clickOnElement(PatientDetailsPageEnum.DELETE_PATIENT);
        isElementDisplayed(PatientDetailsPageEnum.DELETE_DIALOG_BOX);
        fillFields(PatientDetailsPageEnum.REASON_INPUT, reason, true);
        clickOnElement(PatientDetailsPageEnum.DELETE_CONFIRM);
        Assert.assertTrue(isElementDisplayed(PatientDetailsPageEnum.DELETED_MESSAGE));

        return PageFactory.initElements(getDriver(), PatientRecordPage.class);
    }
}
