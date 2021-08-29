import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the local date format of the given date..
 * 
 * @author Gordon Yit
 * @Version CS2103T Semester 1
 */
public class Date {
    protected LocalDate localDate;
    private String[] date;
    
    /**
     * Class constructor.
     * 
     * @param dateString string specifying a date in the form DD/MM/YYYY.
     * @throws DateTimeParseException
     */
    public Date(String dateString) throws DateTimeParseException {
        date = dateString.split("/");
            localDate = LocalDate.parse(String.format("%s-%s-%s", "2021", date[1], date[0]));
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
     * return the local date of the date due.
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
     */
    public static Date convertDateStringToDate(String dateString) throws ParseException {
        SimpleDateFormat stringDateFormat = new SimpleDateFormat("MMM D");
        java.util.Date s = stringDateFormat.parse(dateString);
        SimpleDateFormat dateFormatToDate = new SimpleDateFormat("DD/MM/YYYY");
        return new Date(dateFormatToDate.format(s));
    }
}
