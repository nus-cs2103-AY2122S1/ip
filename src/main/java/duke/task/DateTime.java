package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateTime class which encapsulates a task's date and time. It also enables
 * the date and time to be reformatted.
 */
public class DateTime {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private LocalDate date;
    private LocalTime time;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for DateTime class.
     *
     * @param date The date in yyyy-MM-dd format.
     * @param time The time in HH:mm format.
     */
    DateTime(String date, String time) {
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Constructor for DateTime class.
     *
     * @param date The date in yyyy-MM-dd format.
     * @param startTime The time in HH:mm format.
     * @param endTime The time in HH:mm format.
     */
    DateTime(String date, String startTime, String endTime) {
        this.date = LocalDate.parse(date);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    /**
     * Returns original date used to create the DateTime class.
     *
     * @return String of original date.
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Returns original time used to create the DateTime class.
     *
     * @return String of original time.
     */
    public String getTime() {
        return time.toString();
    }

    /**
     * Returns original time range used to create the DateTime class.
     *
     * @return String of original time range.
     */
    public String getTimeRange() {
        return startTime.toString() + "t" + endTime.toString();
    }

    /**
     * Checks if date has an invalid format.
     *
     * @param date The date to be checked.
     * @return Boolean true if invalid and boolean false otherwise.
     */
    public static boolean isInvalidDate(String date) {
        try {
            DATE_FORMATTER.parse(date);
        } catch (DateTimeParseException e) {
            return true;
        }
        return false;
    }

    /**
     * Checks if time has an invalid format.
     *
     * @param time The time to be checked
     * @return Boolean true if invalid and boolean false otherwise.
     */
    public static boolean isInvalidTime(String time) {
        try {
            TIME_FORMATTER.parse(time);
        } catch (DateTimeParseException e) {
            return true;
        }
        return false;
    }

    /**
     * Formats the input date.
     *
     * @return The String representation of the date in d MMM yyy format.
     */
    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * Formats the input time.
     *
     * @return The String representation of the time in KK.mma format.
     */
    public String getFormattedTime() {
        return time.format(DateTimeFormatter.ofPattern("hh.mma"));
    }

    /**
     * Formats the input time range.
     *
     * @return The String representation of the time range in KK.mma format.
     */
    public String getFormattedTimeRange() {
        return startTime.format(DateTimeFormatter.ofPattern("hh.mma"))
                + " to "
                + endTime.format(DateTimeFormatter.ofPattern("hh.mma"));
    }
}
