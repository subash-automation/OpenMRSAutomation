package openMrs;

//import data.UserData;
//import utils.JsonUtil;

import entity.*;
import org.testng.annotations.DataProvider;
import utility.DateUtil;
import utility.JsonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

            List<Visits> visitsList = new ArrayList<>();

            Visits visits1 = JsonUtil.fromJson(props.getProperty("case1.visits1"), Visits.class);
            visits1.setEndDate(DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE));
            visitsList.add(visits1);

            Visits visits2 = JsonUtil.fromJson(props.getProperty("case1.visits2"), Visits.class);
            visits2.setEndDate(DateUtil.getCurrentDate(DateUtil.SIMPLE_DATE));
            visitsList.add(visits2);

            user.setPatientDemo(basic);
            user.setPatientContact(contact);
            user.setVitals(vitals);
            user.setVisitsList(visitsList);
            return new Object[][]{{user}};
        }

}
