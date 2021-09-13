package duke;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Manages the time inputs of the User for other classes.
 */
public class Time {

    /**
     * Checks whether the User has inputted a date in the correct format.
     * @param strDate the inputted date by the User.
     * @return boolean based on whether the User inputted in the correct format.
     */
    public static boolean validateJavaDate(String strDate) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
        sdfrmt.setLenient(false);

        try {
            Date javaDate = sdfrmt.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Changes the format of the date inputted by the User.
     * @param date the date inputted by the User.
     * @return the formatted date.
     */
    public static String changeDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputtedDate = LocalDate.parse(date, formatter);
        return inputtedDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

}
