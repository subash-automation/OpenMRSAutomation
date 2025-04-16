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
    WebDriver driver;

    @BeforeTest
    public void startTest() {
        driver = getDriver();
        startApplication();
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public void e2eValidation(Users users) throws InterruptedException, AWTException {

        PatientDemographic basic = users.getPatientDemo();
        users.getPatientContact().setPhone(generatePhoneNumber());

        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.isPageLoaded();

        DashboardPage dashboardPage = LoginBL.getInstance().loginWithCreds(users);
        dashboardPage.isPageLoaded();
        RegisterPatientPage registerPatient = dashboardPage.clickOnRegisterPatient();
        registerPatient.isPageLoaded();
        PatientDetailsPage detailsPage = PatientBL.getInstance().registerNewPatient(users);
        detailsPage.isPageLoaded();
        String dob = basic.getDayOfBirth()+","+basic.getMonthOfBirth()+","+basic.getYearOfBirth();
        int age = DateUtil.calculateAge(dob);
        Assert.assertTrue(age == Integer.parseInt(detailsPage.getAgeFromUI()),"Age is not calculated as per DOB");
        VisitsPage visitsPage = detailsPage.startVisit();
        visitsPage.isPageLoaded();
        String projectDir = System.getProperty("user.dir");
        String filePath = projectDir + "\\src\\test\\resources\\files\\DemoFile.txt";
        VisitsBL.getInstance().addAttachment(filePath, "Caption-"+DateUtil.getCurrentTime(DateUtil.TIME_STAMP));
        detailsPage = visitsPage.goBackToPatientDetailPage(users.getPatientDemo());
        Assert.assertTrue(detailsPage.isElementDisplayed(PatientDetailsPageEnum.ATTACHMENTS_THUMBNAIL));
        detailsPage.verifyTheAttachmentUploadTraces(DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE), 1);
        detailsPage.endVsit();
        visitsPage = detailsPage.startVisit();
        VisitsBL.getInstance().addBasicVitalsForBMI(users.getVitals());
        detailsPage = visitsPage.endVisit();
        detailsPage.ensureBMIDetails(users.getVitals());
        detailsPage.verifyTheVitalTraces(DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE), 2);


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
     * Close driver
     */
    @AfterTest
    public void closeTest() {
        quitDriver();
    }
}
