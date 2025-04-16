package businessFlow;

import entity.PatientContact;
import entity.PatientDemographic;
import entity.Users;
import entity.Vitals;
import enums.PatientDetailsPageEnum;
import enums.RegisterPatientPageEnum;
import enums.VisitsPageEnum;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import page.PatientDetailsPage;
import page.RegisterPatientPage;
import utility.DateUtil;
import utility.WaitUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static core.DriverManager.getDriver;

public class PatientBL {

    private void PatientBL() {
    }

    static PatientBL patientBL;

    /**
     * Singleton instance
     * @return
     */
    public static PatientBL getInstance() {
        if (patientBL == null) {
            patientBL = new PatientBL();
        }
        return patientBL;
    }

    RegisterPatientPage patientPage = PageFactory.initElements(getDriver(), RegisterPatientPage.class);

    public PatientDetailsPage registerNewPatient(Users users) {
        PatientDemographic basic = users.getPatientDemo();
        PatientContact contact = users.getPatientContact();
        fillName(basic);
        fillGender(basic);
        fillBirthDetails(basic);
        fillAddress(contact);
        fillPhoneNumber(contact);
        WaitUtil.waitUntil(getDriver(), 3);
        patientPage.clickOnElement(RegisterPatientPageEnum.NEXT);
        ensureDetailsInConfirmPage(users);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        patientPage.clickOnElement(RegisterPatientPageEnum.CONFIRM_BTN);
        WaitUtil.waitUntil(getDriver(), 5);

        return PageFactory.initElements(getDriver(), PatientDetailsPage.class);
    }

    public void fillName(PatientDemographic basic) {
        System.out.println("Enter the Patient Name Information");
        patientPage.fillFields(RegisterPatientPageEnum.GIVEN, basic.getGivenName(), true);
        patientPage.fillFields(RegisterPatientPageEnum.MIDDLE, basic.getMiddleName(), false);
        patientPage.fillFields(RegisterPatientPageEnum.FAMILY, basic.getFamilyName(), true);
        patientPage.clickOnElement(RegisterPatientPageEnum.NEXT);
    }

    public void fillGender(PatientDemographic basic) {
        System.out.println("Enter the Patient Gender Information");
        patientPage.selectGender(basic.getGender());
        patientPage.clickOnElement(RegisterPatientPageEnum.NEXT);
    }

    public void fillBirthDetails(PatientDemographic basic) {
        System.out.println("Enter the Patient DOB Information");
        patientPage.fillFields(RegisterPatientPageEnum.BIRTH_DATE, Integer.toString(basic.getDayOfBirth()), true);
        patientPage.selectValueFromDropdown(RegisterPatientPageEnum.BIRTH_MONTH, DateUtil.getMonthPosition(basic.getMonthOfBirth()));
        patientPage.fillFields(RegisterPatientPageEnum.BIRTH_YEAR, Integer.toString(basic.getYearOfBirth()), true);
        patientPage.clickOnElement(RegisterPatientPageEnum.NEXT);
    }

    public void fillAddress(PatientContact contact) {
        System.out.println("Enter the Patient Address Information");
        patientPage.fillFields(RegisterPatientPageEnum.ADDRESS1, contact.getAddress(), true);
        patientPage.fillFields(RegisterPatientPageEnum.ADDRESS2, contact.getAddress2(), false);
        patientPage.fillFields(RegisterPatientPageEnum.CITY, contact.getCity(), false);
        patientPage.fillFields(RegisterPatientPageEnum.STATE, contact.getState(), false);
        patientPage.fillFields(RegisterPatientPageEnum.COUNTRY, contact.getCountry(), false);
        patientPage.fillFields(RegisterPatientPageEnum.POST_CODE, Integer.toString(contact.getPostCode()), false);
        patientPage.clickOnElement(RegisterPatientPageEnum.NEXT);
    }

    public void fillPhoneNumber(PatientContact contact){
        System.out.println("Enter the Patient Phone Information");
        patientPage.fillFields(RegisterPatientPageEnum.PHONE, contact.getPhone(), true);
        patientPage.clickOnElement(RegisterPatientPageEnum.NEXT);
    }

    public void ensureDetailsInConfirmPage(Users users){
        System.out.println("Ensure Name information populated correctly");
        String[] names = patientPage.getTextFromField(RegisterPatientPageEnum.CONFIRM_NAME).split(",");
        String givenName = names[0].split(":")[1].trim();
        Assert.assertTrue(givenName.equalsIgnoreCase(users.getPatientDemo().getGivenName().trim()), "Given name is mismatching");
        if(names.length==3){
            Assert.assertTrue(names[1].trim().equalsIgnoreCase(users.getPatientDemo().getMiddleName().trim()), "Middle name is mismatching");
            Assert.assertTrue(names[2].trim().equalsIgnoreCase(users.getPatientDemo().getFamilyName().trim()), "Family name is mismatching");
        } else {
            Assert.assertTrue(names[1].trim().equalsIgnoreCase(users.getPatientDemo().getFamilyName().trim()), "Family name is mismatching");
        }

        System.out.println("Ensure Gender information populated correctly");
        String gender = patientPage.getTextFromField(RegisterPatientPageEnum.CONFIRM_GENDER);
        Assert.assertTrue(gender.split(":")[1].trim().equalsIgnoreCase(users.getPatientDemo().getGender().trim()), "Gender is mismatching");

        System.out.println("Ensure Birth information populated correctly");
        String[] dob = patientPage.getTextFromField(RegisterPatientPageEnum.CONFIRM_BIRTHDATE).split(",");
        Assert.assertTrue(dob[0].split(":")[1].trim().equalsIgnoreCase(Integer.toString(users.getPatientDemo().getDayOfBirth())), "Birth date is mismatching");
        Assert.assertTrue(dob[1].trim().equalsIgnoreCase(users.getPatientDemo().getMonthOfBirth()), "Birth month nismatching");
        Assert.assertTrue(dob[2].trim().equalsIgnoreCase(Integer.toString(users.getPatientDemo().getYearOfBirth())), "Birth year is mismatching");

        System.out.println("Ensure Address information populated correctly");
        String[] arr = patientPage.getTextFromField(RegisterPatientPageEnum.CONFIRM_ADDRESS).split(",");
        List<String> conformAddress = Arrays.asList(arr);
        List<String> testDataAddress = new ArrayList<>();
        testDataAddress.add(users.getPatientContact().getAddress());
        testDataAddress.add(users.getPatientContact().getAddress2());
        testDataAddress.add(users.getPatientContact().getCity());
        testDataAddress.add(users.getPatientContact().getState());
        testDataAddress.add(users.getPatientContact().getCountry());
        testDataAddress.add(Integer.toString(users.getPatientContact().getPostCode()));

        Iterator iterator = conformAddress.iterator();
        while (iterator.hasNext()){
            String text = iterator.next().toString().trim();
            if(text.contains(":")){
                text = text.split(":")[1].trim();
            }
            Assert.assertTrue(testDataAddress.contains(text),"Address is mismatching");
        }

        System.out.println("Ensure Phone information populated correctly");
        String phone = patientPage.getTextFromField(RegisterPatientPageEnum.CONFIRM_PHONE).split(":")[1].trim();
        Assert.assertTrue(phone.equalsIgnoreCase(users.getPatientContact().getPhone()), "Phone number mismatching");
    }
}
