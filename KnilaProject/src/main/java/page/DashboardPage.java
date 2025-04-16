package page;

import enums.DashboardPageEnum;
import enums.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.WaitUtil;

import static core.DriverManager.getDriver;

public class DashboardPage extends AbstractBasePage{

    @Override
    public void isPageLoaded() {
        System.out.println("Ensure Dashboard page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), DashboardPageEnum.ALL_APPS.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form!=null && form.isDisplayed();
        System.out.println("Is dashboard page loaded: "+result);
        Assert.assertTrue(result, "Dashboard page is not loaded");
    }

    /**
     * Click Register a patient button
     * @return
     */
    public RegisterPatientPage clickOnRegisterPatient(){
        clickOnElement(DashboardPageEnum.REGISTER_PATIENT);
        return PageFactory.initElements(getDriver(), RegisterPatientPage.class);
    }

    /**
     * Fill the value in the given field
     * @param path
     * @param text
     */
    public void fillFields(DashboardPageEnum path, String text, boolean mandy){
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
     * Click on the given element
     * @param path
     */
    public void clickOnElement(DashboardPageEnum path){
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.FIVE_SEC);
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
    public void clickOnElement(DashboardPageEnum path, String value){
        WebElement element = WaitUtil.waitForVisibility(getDriver(), By.xpath(String.format(path.getXpath(), value)), Timeout.FIVE_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }
}
