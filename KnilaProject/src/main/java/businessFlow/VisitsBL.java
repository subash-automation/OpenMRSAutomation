package businessFlow;

import entity.Vitals;
import enums.Timeout;
import enums.VisitsPageEnum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import page.VisitsPage;
import utility.WaitUtil;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static core.DriverManager.getDriver;

public class VisitsBL {

    private void VisitsBL() {
    }

    static VisitsBL visitsBL;

    /**
     * Singleton instance
     * @return
     */
    public static VisitsBL getInstance() {
        if (visitsBL == null) {
            visitsBL = new VisitsBL();
        }
        return visitsBL;
    }

    VisitsPage visitsPage = PageFactory.initElements(getDriver(), VisitsPage.class);

    /**
     * Add attachment
     * @param filePath
     * @param caption
     * @throws AWTException
     * @throws InterruptedException
     */
    public void addAttachment(String filePath, String caption) throws AWTException, InterruptedException {

        System.out.println("Add attachment to patient");
        visitsPage.clickOnElement(VisitsPageEnum.ATTACHMENTS);
        WaitUtil.waitUntil(getDriver(), 5);
        visitsPage.isAttachmentPageLoaded();
        visitsPage.clickOnElement(VisitsPageEnum.DROP_FILE);
        System.out.println("Open file: "+filePath);
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();
        Thread.sleep(5000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(5000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        System.out.println("File added successfully");

        visitsPage.fillFields(VisitsPageEnum.CAPTION, caption, true);
        visitsPage.checkAndClickUploadBtn();
        Assert.assertTrue(visitsPage.isElementDisplayed(VisitsPageEnum.TOASTER_SUCCESS));
    }

    /**
     * Add height and weight and check BMI
     * @param vitals
     */
    public void addBasicVitalsForBMI(Vitals vitals){
        visitsPage.clickOnElement(VisitsPageEnum.CAPTURE_VITALS);
        WaitUtil.waitUntil(getDriver(), 5);
        visitsPage.fillFields(VisitsPageEnum.HEIGHT_INPUT,Double.toString(vitals.getHeight()), true);
        visitsPage.clickOnElement(VisitsPageEnum.WEIGHT_TAB);
        visitsPage.fillFields(VisitsPageEnum.WEIGHT_INPUT, Double.toString(vitals.getWeight()), true);
        visitsPage.clickOnElement(VisitsPageEnum.BMI_TAB);
        String bmi = visitsPage.getTextFromField(VisitsPageEnum.BMI_FROM_UI);
        Assert.assertTrue(Double.parseDouble(bmi)==calulateBMI(vitals), "BMI generated in BMI page is not correct");
        visitsPage.clickOnElement(VisitsPageEnum.SAVE_FORM);
        visitsPage.clickOnElement(VisitsPageEnum.SAVE_BTN);
    }

    /**
     * Calculate BMI
     * @param vitals
     * @return
     */
    public double calulateBMI(Vitals vitals){
        double heightM = vitals.getHeight() / 100;
        double bmi = vitals.getWeight() / (heightM * heightM);

        BigDecimal bd = new BigDecimal(bmi);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        bmi = bd.doubleValue();
        System.out.println("The BMI is: "+bmi);

        return bmi;
    }
}
