package entity;

import java.util.List;

public class Users {

    private String userName;

    private String password;

    private String location;

    private PatientDemographic patientDemo;

    private PatientContact patientContact;

    private Vitals vitals;

    private List<Visits> visitsList;


    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public PatientDemographic getPatientDemo() {
        return patientDemo;
    }
    public void setPatientDemo(PatientDemographic patientDemo) {
        this.patientDemo = patientDemo;
    }

    public PatientContact getPatientContact() {
        return patientContact;
    }
    public void setPatientContact(PatientContact patientContact) {
        this.patientContact = patientContact;
    }

    public Vitals getVitals() {
        return vitals;
    }
    public void setVitals(Vitals vitals) {
        this.vitals = vitals;
    }

    public List<Visits> getVisitsList() {
        return visitsList;
    }
    public void setVisitsList(List<Visits> visitsList) {
        this.visitsList = visitsList;
    }
}
