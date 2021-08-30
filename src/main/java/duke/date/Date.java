package duke.date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.lang.NumberFormatException;
import java.time.format.DateTimeParseException;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;


/**
 * Class that stores and format date time values
 */
public class Date {
    private static final String MORNING_INDICATOR = "am";
    private static final String EVENING_INDICATOR = "pm";
    private final LocalDate globalDate;
    private final LocalTime globalTime;
    
    /**
     * Initializes the date class with 
     * a given string form of date and time 
     * 
     * @param dateAndTime String of the date and time user input
     * @throws WrongTimeFormatException if the expected time format given by user is wrong
     * @throws WrongDateFormatException if the expected date format given by user is wrong
     */
    public Date(String dateAndTime) throws WrongTimeFormatException, WrongDateFormatException {
        String[] dateTimeSplit = dateAndTime.split(" ");
        LocalDate inputDate;
        LocalTime inputTime;
        inputDate = formatDate(dateTimeSplit[0]);
        inputTime = formatTime(dateTimeSplit[1]);
        this.globalDate = inputDate;
        this.globalTime = inputTime;
    }

    private LocalTime formatTime(String timeString) throws NumberFormatException, WrongTimeFormatException, DateTimeParseException {
        String[] hhmm = timeString.split("[-/:]+");
        LocalTime time;
        if (hhmm.length == 1) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            time = LocalTime.parse(timeString, timeFormatter);
        } else if (hhmm.length > 2) {
            throw new WrongTimeFormatException();
        } else {
            int hours = Integer.valueOf(hhmm[0]);
            int mins = Integer.valueOf(hhmm[1]);
            time = LocalTime.of(hours, mins);
        }
        return time;
    }

    private LocalDate formatDate(String dateString) throws NumberFormatException, WrongDateFormatException {
        String[] ddmmyyyy = dateString.split("[-/:]+");
        if (ddmmyyyy.length != 3) {
            throw new WrongDateFormatException();
        }
        int year = Integer.valueOf(ddmmyyyy[2]);
        int month = Integer.valueOf(ddmmyyyy[1]);
        int date = Integer.valueOf(ddmmyyyy[0]);
        LocalDate currentDate = LocalDate.of(year, month, date);
        return currentDate;
    }

    /**
     * returns the proper format of date 
     * and time used for ONLY storing
     * 
     * @return String of date and time format
     */
    public String getOriginalFormat() {
        DateTimeFormatter Dateformat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.globalDate.format(Dateformat) + " " + this.globalTime.toString();
    }

    @Override
    public String toString() {
        return this.globalDate.getMonth() + " " + this.globalDate.getDayOfMonth() +
        " " + this.globalDate.getYear() + " " + this.getStringTime();
    }

    private String getStringTime() {
        int timeInHours = this.globalTime.getHour();
        int timeInMins = this.globalTime.getMinute();
        if (timeInHours == 12) {
            if (timeInMins > 0) {
                return String.valueOf(timeInHours) + "." + timeInMins + EVENING_INDICATOR;
            }
            return String.valueOf(timeInHours) + EVENING_INDICATOR;
        } else if (timeInHours > 12) {
            if (timeInMins > 0) {
                return String.valueOf(timeInHours - 12) + "." + timeInMins + EVENING_INDICATOR;
            }
            return String.valueOf(timeInHours - 12) + EVENING_INDICATOR;
        } else {
            if (timeInMins > 0) {
                return String.valueOf(timeInHours) + "." + timeInMins + MORNING_INDICATOR;
            }
            return String.valueOf(timeInHours) + MORNING_INDICATOR;
        }
    }
}
