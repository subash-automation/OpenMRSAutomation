package page;

import enums.LoginPageEnum;
import enums.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utility.WaitUtil;

import static core.DriverManager.getDriver;

public class LoginPage extends AbstractBasePage {

    /**
     * Check the element displayed
     * @param path
     * @return
     */
    boolean isElementDisplayed(LoginPageEnum path) {
        System.out.println("Check the"+path.getDesc()+" is loaded or not");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        boolean result = element!=null && element.isDisplayed();
        System.out.println("Is "+path.getDesc()+" loaded: "+result);
        return result;
    }

    /**
     * Check the element displayed
     * @param path
     * @param value
     * @return
     */
    boolean idElementDisplayed(LoginPageEnum path, String value) {
        System.out.println("Check the"+path.getDesc()+" is loaded or not");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), By.xpath(String.format(path.getXpath(), value)), Timeout.TEN_SEC);
        boolean result = element!=null && element.isDisplayed();
        System.out.println("Is "+path.getDesc()+" loaded: "+result);
        return result;
    }

    /**
     * Ensure the page is loaded
     * @return
     */
    @Override
    public void isPageLoaded() {
        System.out.println("Ensure Login page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), LoginPageEnum.LOGIN_FORM.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form!=null && form.isDisplayed();
        System.out.println("Is login page loaded: "+result);
        Assert.assertTrue(result, "Login page is not loaded");
    }

    /**
     * Fill the value in the given field
     * @param path
     * @param text
     */
    public void fillFields(LoginPageEnum path, String text, boolean mandy){
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
    public void clickOnElement(LoginPageEnum path){
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
    public void clickOnElement(LoginPageEnum path, String value){
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
