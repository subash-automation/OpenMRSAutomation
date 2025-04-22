package openMrs;

import businessFlow.LoginBL;
import businessFlow.PatientBL;
import businessFlow.VisitsBL;
import core.AutomationListener;
import entity.PatientDemographic;
import entity.Users;
import enums.PatientDetailsPageEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.*;
import utility.DateUtil;

import java.awt.*;
import java.util.Random;

import static core.DriverManager.*;

@Listeners(AutomationListener.class)
public class EndToEndTest {

    /** The webdriver */
    WebDriver driver;

    @BeforeTest
    public void startTest() {
        driver = getDriver();
        startApplication();
    }

    /**
     * End to End Validation
     * @param users
     * @throws InterruptedException
     * @throws AWTException
     */
    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public void e2eValidation(Users users) throws InterruptedException, AWTException {

        PatientDemographic basic = users.getPatientDemo();
        users.getPatientContact().setPhone(generatePhoneNumber());

        stepLog("Login with valid credentials");
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.isPageLoaded();
        DashboardPage dashboardPage = LoginBL.getInstance().loginWithCreds(users);
        dashboardPage.isPageLoaded();

        stepLog("Validate Register a Patient flow");
        RegisterPatientPage registerPatient = dashboardPage.clickOnRegisterPatient();
        registerPatient.isPageLoaded();
        PatientDetailsPage detailsPage = PatientBL.getInstance().registerNewPatient(users);
        detailsPage.isPageLoaded();
        String dob = basic.getDayOfBirth()+","+basic.getMonthOfBirth()+","+basic.getYearOfBirth();
        int age = DateUtil.calculateAge(dob);
        Assert.assertTrue(age == Integer.parseInt(detailsPage.getAgeFromUI()),"Age is not calculated as per DOB");

        stepLog("start visit and validate add attachment flow");
        VisitsPage visitsPage = detailsPage.startVisit();
        visitsPage.isPageLoaded();
        String projectDir = System.getProperty("user.dir");
        String filePath = projectDir + "\\src\\test\\resources\\files\\DemoFile.txt";
        VisitsBL.getInstance().addAttachment(filePath, "Caption-"+DateUtil.getCurrentTime(DateUtil.TIME_STAMP));
        detailsPage = visitsPage.goBackToPatientDetailPage(users.getPatientDemo());
        Assert.assertTrue(detailsPage.isElementDisplayed(PatientDetailsPageEnum.ATTACHMENTS_THUMBNAIL));
        detailsPage.verifyTheAttachmentUploadTraces(DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE), 1);

        stepLog("End visit from Patient Details page");
        detailsPage.endVsit();

        stepLog("Start visit and validate add vitals flow");
        visitsPage = detailsPage.startVisit();
        VisitsBL.getInstance().addBasicVitalsForBMI(users.getVitals());
        detailsPage = visitsPage.endVisit();
        detailsPage.goBackToPatientDetailPage(users.getPatientDemo());
        detailsPage.isPageLoaded();
        detailsPage.ensureBMIDetails(users.getVitals());
        detailsPage.verifyTheVitalTraces(DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE), 2);

        stepLog("Validate Merge visits flow");
        MergePage mergePage = detailsPage.startMerge();
        mergePage.mergeVisits(users.getVisitsList());
        detailsPage = mergePage.clickOnReturn();
        detailsPage.isPageLoaded();
        detailsPage.verifyRecentVisitMerge(DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE),users.getVisitsList());

        stepLog("Validate Add past date flow");
        detailsPage.clickAddPastVist();
        detailsPage.checkFutureDatesClickable();

        stepLog("Validate Delete patient flow");
        PatientRecordPage recordPage = detailsPage.deletePatient("Reason");
        recordPage.isPageLoaded();

        stepLog("Verify patient was not listed");
        recordPage.verifyDeletedPatientNotListed(users.getPatientDemo());

    }

    /**
     * Generate a random phone number
     * @return
     */
    private String generatePhoneNumber(){
        Random random = new Random();
        long number = 9_000_000_000L + (long)(random.nextDouble() * 1_000_000_000L);
        return Long.toString(number);
    }

    /**
     * Special log for steps
     * @param text
     */
    private void stepLog(String text){
        System.out.println("\n***"+text.toUpperCase()+" ***\n");
    }

    /**
     * Close driver
     */
    @AfterTest
    public void closeTest() {
        quitDriver();
    }
}
