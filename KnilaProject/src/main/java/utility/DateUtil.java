package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {

    public static final String FULL_DATE_FORMAT = "d,MMMM,yyyy";
    public static final String TIME_STAMP = "HH:mm:ss";
    public static final String SIMPLE_DATE = "dd.MMM.yyyy";

    /**
     * Calculate age from DOB
     * @param dob
     * @return
     */
    public static int calculateAge(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FULL_DATE_FORMAT, Locale.ENGLISH);
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        return age.getYears();
    }

    /**
     * Get position of month
     * @param monthName
     * @return
     */
    public static int getMonthPosition(String monthName) {
        try {
            Month month = Month.valueOf(monthName.toUpperCase());
            return month.getValue();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid month name: " + monthName);
            return -1;
        }
    }

    /**
     * Get current time
     * @param format
     * @return
     */
    public static String getCurrentTime(String format){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.TIME_STAMP);
        String formattedTimestamp = now.format(formatter);

        return formattedTimestamp;
    }

    /**
     * get current date
     * @param format
     * @return
     */
    public static String getCurrentDate(String format){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
        String formattedDate = today.format(formatter);
        System.out.println("Current Date: " + formattedDate);

        return formattedDate;
    }
}
