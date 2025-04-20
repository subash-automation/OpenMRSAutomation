package entity;

public class Visits {

    private String startDate; //Should be in format of DateUtil.SIMPLE_DATE

    private String endDate; //Should be in format of DateUtil.SIMPLE_DATE

    private String encounter;


    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEncounter() {
        return encounter;
    }
    public void setEncounter(String encounter) {
        this.encounter = encounter;
    }

}
