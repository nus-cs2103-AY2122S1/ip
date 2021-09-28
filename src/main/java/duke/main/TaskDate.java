package duke.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Represents the local date format of the given date..
 *
 * @author Gordon Yit
 * @version CS2103T Semester 1
 */
public class TaskDate {
    protected LocalDate localDate;
    /**
     * Class constructor.
     *
     * @param dateString given in the form dd/mm/yyyy or dd/mm.
     * @throws DateTimeParseException exception caused by improper time format.
     */
    public TaskDate(String dateString) throws DukeException {
        String[] dayMonthYear = dateString.split("/");
        try {
            localDate = LocalDate.parse(reformatDateString(dayMonthYear));
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.Exceptions.DateTimeParseException);
        }
    }

    private String reformatDateString(String ... dayMonthYear) throws DukeException {
        try {
            return String.format("%s-%s-%s", "2021", dayMonthYear[1], dayMonthYear[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.DateTimeParseException);
        }
    }
    /**
     * Returns string format of the datetime.
     *
     * @return date in form DD MMM YYYY.
     */
    @Override
    public String toString() {
        return localDate.format(getDateFormat());
    }

    private DateTimeFormatter getDateFormat() {
        //formats the date in the form mmm dd, ie Jan 01.
        return DateTimeFormatter.ofPattern("MMM dd");
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
     * @return true if both dates are equal, false otherwise.
     */
    public boolean equals(String dateString) throws DukeException {
        LocalDate date = LocalDate.parse(reformatDateString(dateString.split("/")));
        return localDate.isEqual(date);
    }

    /**
     * Converts a given date string into a date object.
     *
     * @param dateString in form of DD MMM YYYY.
     * @return a date object corresponding to the date string.
     * @throws ParseException exception caused by parsing date in improper format.
     */
    public static TaskDate convertDateStringToDate(String dateString) throws DukeException {
        try {
            Date monthDayDate = getFormattedDate("MMM D", dateString);
            SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
            return new TaskDate(getFormattedDateString(dateFormat, monthDayDate));
        } catch (ParseException e) {
            throw new DukeException(DukeException.Exceptions.DateTimeParseException);
        }
    }
    private static Date getFormattedDate(String dateFormatString, String dateString) throws ParseException {
        SimpleDateFormat monthDayFormat = new SimpleDateFormat(dateFormatString);
        return monthDayFormat.parse(dateString);
    }
    private static String getFormattedDateString(SimpleDateFormat simpleDateFormat, Date monthDayDate) {
        return simpleDateFormat.format(monthDayDate);
    }
}
