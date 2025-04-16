package openMrs;

//import data.UserData;
//import utils.JsonUtil;

import entity.PatientContact;
import entity.PatientDemographic;
import entity.Users;
import entity.Vitals;
import org.testng.annotations.DataProvider;
import utility.DateUtil;
import utility.JsonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestDataProvider {

    static Properties props;
    public  static void loadConfig()  throws FileNotFoundException, IOException {

        props = new Properties();
        String filePath = "src/test/resources/properties/testData.properties";
        FileInputStream fis = new FileInputStream(filePath);
        props.load(fis);
    }

        @DataProvider(name = "testData")
        public static Object[][] getTestData() throws IOException {

            loadConfig();
            Users user = JsonUtil.fromJson(props.getProperty("case1.userDetail"), Users.class);
            PatientDemographic basic = JsonUtil.fromJson(props.getProperty("case1.patientDetail"), PatientDemographic.class);
            basic.setFamilyName(DateUtil.getCurrentTime(DateUtil.TIME_STAMP));
            PatientContact contact = JsonUtil.fromJson(props.getProperty("case1.patientContact"), PatientContact.class);
            Vitals vitals = JsonUtil.fromJson(props.getProperty("case1.vitals"), Vitals.class);

            user.setPatientDemo(basic);
            user.setPatientContact(contact);
            user.setVitals(vitals);
            return new Object[][]{{user}};
        }

}
