package page;

import entity.Visits;
import enums.MergePageEnum;
import enums.Timeout;
import enums.VisitsPageEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.WaitUtil;

import java.util.Iterator;
import java.util.List;

import static core.DriverManager.getDriver;

public class MergePage extends AbstractBasePage {

    @Override
    public void isPageLoaded() {
        System.out.println("Ensure Visits page is loaded");
        WebElement form = WaitUtil.waitForVisibility(getDriver(), MergePageEnum.TABLE.getLocator(), Timeout.TWENTY_SEC);
        boolean result = form != null && form.isDisplayed();
        System.out.println("Is visits page loaded: " + result);
        Assert.assertTrue(result, "Visits page is not loaded");
    }

    /**
     * Click on the given element
     *
     * @param path
     */
    public void clickOnElement(MergePageEnum path) {
        System.out.println("Click on " + path.getDesc() + " element");
        WebElement element = WaitUtil.waitForVisibility(getDriver(), path.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, path.getDesc() + " element is NULL");
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Click on the given element
     *
     * @param path
     */
    public void clickOnElement(MergePageEnum path, String value) {
        WebElement element = WaitUtil.waitForVisibility(getDriver(), By.xpath(String.format(path.getXpath(), value)), Timeout.FIVE_SEC);
        Assert.assertNotNull(element, path.getDesc() + " element is NULL");
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Merge the list of visits
     *
     * @param visitsList
     */
    public void mergeVisits(List<Visits> visitsList) {
        Assert.assertFalse(visitsList.isEmpty(), "Visits list is empty");
        for (Visits visits : visitsList) {
            String encounterFromList = "   " + visits.getEncounter().trim();
            String xPath = String.format(MergePageEnum.CHECKBOX.getXpath(), encounterFromList, visits.getEndDate().trim());
            WebElement checkBox = WaitUtil.waitForVisibility(getDriver(), By.xpath(xPath), Timeout.TEN_SEC);
            if (checkBox.isDisplayed()) {
                checkBox.click();
            }
        }

        WebElement merge = WaitUtil.waitForVisibility(getDriver(), MergePageEnum.MERGE_BTN_ENABLED.getLocator(), Timeout.TEN_SEC);
        if (merge.isDisplayed()) {
            merge.click();
        } else {
            System.out.println("Merge button was not enabled/displayed to click");
        }
    }

    /**
     * Click on the given element
     */
    public PatientDetailsPage clickOnReturn() {
        WebElement element = WaitUtil.waitForVisibility(getDriver(), MergePageEnum.RETURN.getLocator(), Timeout.TEN_SEC);
        Assert.assertNotNull(element, "Return button element is NULL");
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        }

        return PageFactory.initElements(getDriver(), PatientDetailsPage.class);
    }
}
