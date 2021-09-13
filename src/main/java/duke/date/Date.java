package duke.date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;


/**
 * Class that stores and format date time values.
 */
public class Date {

    /**
     * Indicates morning time before 12pm.
     */
    private static final String MORNING_INDICATOR = "am";

    /**
     * Indicates afternoon to evening time after 12pm
     * and before 12am.
     */
    private static final String EVENING_INDICATOR = "pm";

    /**
     * Indicates exact noon timing of 12.00pm.
     */
    private static final int NOON = 12;

    /**
     * Indicates universal date length of 3
     * with date, month and year.
     */
    private static final int LENGTH_OF_DATE = 3;

    /**
     * Keeps track of date of event or deadline task.
     */
    private final LocalDate globalDate;

    /**
     * Keeps track of time of event or deadline task.
     */
    private final LocalTime globalTime;

    /**
     * Initializes the date class with
     * a given string form of date and time.
     * @param dateAndTime String of the date and time user input.
     * @throws WrongTimeFormatException if the expected time
     * format given by user is wrong.
     * @throws WrongDateFormatException if the expected date
     * format given by user is wrong.
     */
    public Date(
            String dateAndTime) throws WrongTimeFormatException,
            WrongDateFormatException {
        String[] dateTimeSplit = dateAndTime.split(" ");
        LocalDate inputDate;
        LocalTime inputTime;
        inputDate = formatDate(dateTimeSplit[0]);
        inputTime = formatTime(dateTimeSplit[1]);
        this.globalDate = inputDate;
        this.globalTime = inputTime;
    }

    /**
     * Utilized by the the schedule command class
     * to have compare dates against all the task in taskList.
     * @param commandWithDate Array of string that.
     * consists of command name and the date input from user.
     * @throws WrongDateFormatException throws error when the date.
     * format is not in the standards acceptable of 12:12:2020,
     * 12-12-2020 or 12/12/2020.
     */
    public Date(String[] commandWithDate) throws WrongDateFormatException {
        String actualDate = commandWithDate[1];
        LocalDate inputDate = formatDate(actualDate);
        this.globalDate = inputDate;
        this.globalTime = LocalTime.now();
    }

    private LocalTime formatTime(
            String timeString) throws NumberFormatException,
            WrongTimeFormatException, DateTimeParseException {
        String[] hourAndMinutes = timeString.split("[-/:]+");
        LocalTime time;
        if (hourAndMinutes.length == 1) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.
                    ofPattern("HHmm");
            time = LocalTime.parse(timeString, timeFormatter);
        } else if (hourAndMinutes.length > 2) {
            throw new WrongTimeFormatException();
        } else {
            int hours = Integer.valueOf(hourAndMinutes[0]);
            int minutes = Integer.valueOf(hourAndMinutes[1]);
            time = LocalTime.of(hours, minutes);
        }
        return time;
    }

    private LocalDate formatDate(
            String dateString) throws NumberFormatException,
            WrongDateFormatException {
        String[] dateMonthYear = dateString.split("[-/:]+");
        if (dateMonthYear.length != LENGTH_OF_DATE) {
            throw new WrongDateFormatException();
        }
        int year = Integer.valueOf(dateMonthYear[2]);
        int month = Integer.valueOf(dateMonthYear[1]);
        int date = Integer.valueOf(dateMonthYear[0]);
        LocalDate currentDate = LocalDate.of(year, month, date);
        return currentDate;
    }

    /**.
     * returns the proper format of date
     * and time used for ONLY storing.
     * @return String of date and time format.
     */
    public String getOriginalFormat() {
        DateTimeFormatter dateFormat = DateTimeFormatter.
                ofPattern("dd-MM-yyyy");
        return this.globalDate.format(dateFormat)
                + " "
                + this.globalTime.toString();
    }

    @Override
    public String toString() {
        return this.globalDate.getMonth()
                + " "
                + this.globalDate.getDayOfMonth()
                + " "
                + this.globalDate.getYear()
                + " "
                + this.getStringTime();
    }

    /**
     * checks if 2 dates are equivalent.
     * @param otherDate Another date object to be compared.
     * @return boolean if 2 dates are equivalent.
     */
    public boolean isEquivalentDate(Date otherDate) {
        return this.globalDate.equals(otherDate.globalDate);
    }

    private String getStringTime() {
        int timeInHours = this.globalTime.getHour();
        int timeInMinutes = this.globalTime.getMinute();
        if (timeInHours == NOON) {
            return this.getStringHelper(
                (timeInMinutes > 0),
                    timeInMinutes,
                String.valueOf(timeInHours),
                EVENING_INDICATOR);
        } else if (timeInHours > NOON) {
            return this.getStringHelper(
                (timeInMinutes > 0),
                timeInMinutes,
                String.valueOf(timeInHours - NOON),
                EVENING_INDICATOR);
        }
        return this.getStringHelper(
            (timeInMinutes > 0),
            timeInMinutes,
            String.valueOf(timeInHours),
            MORNING_INDICATOR);
    }

    private String getStringHelper(
            boolean hasMinutes,
            int timeInMinutes,
            String timeString,
            String indicator) {
        if (hasMinutes) {
            return timeString + "." + timeInMinutes + indicator;
        }
        return timeString + indicator;
    }
}
