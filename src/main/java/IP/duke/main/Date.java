package IP.duke.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the local date format of the given date..
 * 
 * @author Gordon Yit
 * @version CS2103T Semester 1
 */
public class Date {
    protected LocalDate localDate;
    private String[] date;
    private final int MAX_COMP = 3;
    private final int MIN_COMP = 2;
    /**
     * Class constructor.
     * 
     * @param dateComponents components of the date
     * @throws DateTimeParseException exception caused by improper time format.
     */
    public Date(String ... dateComponents) throws DukeException {
        try {
            localDate = LocalDate.parse(String.format("%s-%s-%s", "2021", dateComponents[1], dateComponents[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(e);
        } catch (DateTimeParseException e) {
            throw new DukeException(e);
        }
    }

    /**
     * Returns string format of the datetime.
     *
     * @return date in form DD MMM YYYY.
     */
    @Override
    public String toString() {
        String dateString = localDate.format(DateTimeFormatter.ofPattern("MMM dd"));
        return dateString;
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

    /**
     * converts a given date string into a date object.
     * 
     * @param dateString in form of DD MMM YYYY.
     * @return a date object corresponding to the date string.
     * @throws ParseException exception caused by parsing date in improper format.
     */
    public static Date convertDateStringToDate(String dateString) throws DukeException {
        try {
            SimpleDateFormat monthDayFormat = new SimpleDateFormat("MMM D");
            java.util.Date monthDayDate = monthDayFormat.parse(dateString);
            SimpleDateFormat dateFormatToDate = new SimpleDateFormat("DD/MM/YYYY");
            return new Date(dateFormatToDate.format(monthDayDate));
        } catch (ParseException e) {
            throw new DukeException(e);
        }
    }
}
