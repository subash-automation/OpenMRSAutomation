package page;

import enums.RegisterPatientPageEnum;
import enums.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utility.WaitUtil;

import static core.DriverManager.getDriver;

public class RegisterPatientPage extends AbstractBasePage{

    @Override
    public void isPageLoaded() {
        System.out.println("Ensure patient register page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), RegisterPatientPageEnum.PATIENT_FORM.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form!=null && form.isDisplayed();
        System.out.println("Is patient register page loaded: "+result);
        Assert.assertTrue(result, "Register page is not loaded");
    }

    /**
     * Fill the value in the given field
     * @param path
     * @param text
     */
    public void fillFields(RegisterPatientPageEnum path, String text, boolean mandy) {
        if(mandy){
            System.out.println("Enter value :" + text + " in mandatory field " + path.getDesc());
            Assert.assertTrue(text!=null && !text.isEmpty(), "Entering value is null or emplty for a mandatory field");
        }
        System.out.println("Enter value :" + text + " in field " + path.getDesc());
        WebElement field = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(field, path.getDesc() + " field is not displayed");
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    /**
     * Click on the given element
     * @param path
     */
    public void clickOnElement(RegisterPatientPageEnum path){
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
    public void clickOnElement(RegisterPatientPageEnum path, String value){
        System.out.println("Click on "+path.getDesc()+" element");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), By.xpath(String.format(path.getXpath(), value)), Timeout.TEN_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        try{
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Select gender from dropdown
     * @param value
     */
    public void selectGender(String value){
        System.out.println("Select "+value+" from gender dropdown");
        WebElement dropDown = WaitUtil.waitForVisibility(getDriver(), RegisterPatientPageEnum.GENDER_DD.getLocator(), Timeout.TEN_SEC);
        Assert.assertTrue(dropDown!=null && dropDown.isDisplayed(), "Gender dropdown is not displayed");
        Select select = new Select(dropDown);
        if (value.equalsIgnoreCase("Male"))
            select.selectByIndex(0);
        else if (value.equalsIgnoreCase("Female"))
            select.selectByIndex(1);
        else
            System.out.println("Given value "+value+" is not available in gender dropdown");
    }

    /**
     * Select value from dropdown
     * @param path
     * @param value
     */
    public void selectValueFromDropdown(RegisterPatientPageEnum path, int value){
        System.out.println("Select "+value+" from "+path.getDesc()+" dropdown");
        WebElement dd = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(dd, "Dropdown is NULL");
        Select select = new Select(dd);
        select.selectByIndex(value);
    }

    /**
     * get text from the field
     * @param path
     * @return
     */
    public String getTextFromField(RegisterPatientPageEnum path){
        System.out.println("Get text from "+path.getDesc()+" field");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, path.getDesc()+" element is NULL");
        System.out.println("Text from element is: "+element.getText().trim());
        return element.getText().trim();
    }
}
