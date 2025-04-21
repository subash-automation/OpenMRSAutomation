package page;

import entity.PatientDemographic;
import enums.PatientDetailsPageEnum;
import enums.PatientRecordPageEnum;
import enums.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utility.DateUtil;
import utility.WaitUtil;

import static core.DriverManager.getDriver;

public class PatientRecordPage extends AbstractBasePage{

    @Override
    public void isPageLoaded() {
        System.out.println("Ensure patient records page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), PatientRecordPageEnum.CONTENT.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form!=null && form.isDisplayed();
        System.out.println("Is patient records page loaded: "+result);
        Assert.assertTrue(result, "Records page is not loaded");
    }

    /**
     * Fill the value in the given field
     * @param path
     * @param text
     */
    public void fillFields(PatientRecordPageEnum path, String text, boolean mandy){
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
     * Check that patent not listed
     * @param basic
     */
    public void verifyDeletedPatientNotListed(PatientDemographic basic){
        String fullName = basic.getGivenName()+" "+basic.getMiddleName()+" "+basic.getFamilyName();
        fillFields(PatientRecordPageEnum.SEARCH_FIELD, fullName,true);
        String dob = DateUtil.convertDOBtoSimpleDateFormat(basic);
        String xPath = String.format(PatientRecordPageEnum.SEARCH_RESULT_ROW.getXpath(), dob, fullName);
        WebElement result = WaitUtil.waitForVisibility(getDriver(), By.xpath(xPath), Timeout.TEN_SEC);
        Assert.assertFalse(result!=null && result.isDisplayed(), "Patient is listed after deletion");
    }
}
