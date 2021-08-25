package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * TaskTime Class to handle date time formatting
 */
public class TaskTime { //TaskTime used to handle the timings
    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIMEFORMAT = DateTimeFormatter.ofPattern("hhmma");

    /**
     * To convert a fixed input of YYYY-MM-DD and HH:MM to MMM dd yyyy and hhmma
     *
     * @param inputTime String of input date or date AND time in YYY-MM-DD and HH:MM
     * @return the time in the converted format
     */
    public static String convertDateTimeFormat(String inputTime) {
        String[] split = inputTime.split(" ");
        LocalDate date = LocalDate.parse(split[0].trim());
        if (split.length == 1) {
            return date.format(DATEFORMAT);
        } else {
            LocalTime time = LocalTime.parse(split[1].trim());
            return date.format(DATEFORMAT) + " " + time.format(TIMEFORMAT);
        }
    }

    /**
     * To convert a fixed input of MMM dd yyyy and hhmma to YYY-MM-DD and HH:MM
     * Used for reading an already made txt file
     *
     * @param inputTime String of input date or date AND time in MMM dd yyyy and hhmma
     * @return the time in the converted format
     */
    public static String unconvertDateTime(String inputTime) {
        String dateString = inputTime.substring(0, 11);
        String timeString = inputTime.substring(11);
        LocalDate date = LocalDate.parse(dateString.trim(), DATEFORMAT);
        if (timeString.equals("")) {
            return date.toString();
        } else {
            LocalTime time = LocalTime.parse(timeString.trim(), TIMEFORMAT);
            return date.toString() + " " + time.toString();
        }
    }
}