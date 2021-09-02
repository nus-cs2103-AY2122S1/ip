package duke.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the local date format of the given date..
 *
 * @author Gordon Yit
 * @version CS2103T Semester 1
 */
public class Date {
    private LocalDate localDate;

    /**
     * Class constructor.
     *
     * @param dateString string specifying a date in the form DD/MM/YYYY.
     * @throws DateTimeParseException exception caused by improper time format.
     */
    public Date(String dateString) throws DateTimeParseException {
        String[] dateParts = dateString.split("/");
        localDate = LocalDate.parse(String.format("%s-%s-%s", "2021", dateParts[1], dateParts[0]));
    }

    /**
     * converts a given date string into a date object.
     *
     * @param dateString in form of DD MMM YYYY.
     * @return a date object corresponding to the date string.
     * @throws ParseException exception caused by parsing date in improper format.
     */
    public static Date convertDateStringToDate(String dateString) throws ParseException {
        SimpleDateFormat monthDayFormat = new SimpleDateFormat("MMM d");
        java.util.Date monthDayDate = monthDayFormat.parse(dateString);
        SimpleDateFormat dateFormatToDate = new SimpleDateFormat("dd/MM/yyyy");
        return new Date(dateFormatToDate.format(monthDayDate));
    }

    /**
     * Returns string format of the datetime.
     *
     * @return date in form DD MMM YYYY.
     */
    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd"));
    }

    /**
     * Retrieves the local date object.
     *
     * @return the local date of the date due.
     */
    public LocalDate getLocalDate() {
        return this.localDate;
    }

    /**
     * Checks if a given local date equals this local date.
     *
     * @param dateString the given local date object.
     * @return true if both local dates are equal, false otherwise.
     */
    public boolean isSameDate(String dateString) {
        return toString().equals(dateString);
    }
}
