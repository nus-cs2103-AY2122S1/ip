package pika.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * TaskTime Class to handle date time formatting.
 */
public class TaskTime { //TaskTime used to handle the timings
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hhmma");

    /**
     * Converts a fixed input of YYYY-MM-DD and HH:MM to MMM dd yyyy and hhmma.
     *
     * @param inputTime String of input date or date AND time in YYY-MM-DD and HH:MM.
     * @return Time in the converted format.
     */
    public static String convertDateTimeFormat(String inputTime) {
        assert inputTime != null : "Pika Pi, this is not valid!";
        String[] split = inputTime.split(" ");
        LocalDate date = LocalDate.parse(split[0].trim());
        if (split.length == 1) {
            return date.format(DATE_FORMAT);
        } else {
            LocalTime time = LocalTime.parse(split[1].trim());
            return date.format(DATE_FORMAT) + " " + time.format(TIME_FORMAT);
        }
    }

    /**
     * Converts a fixed input of MMM dd yyyy and hhmma to YYY-MM-DD and HH:MM.
     * Used for reading an already made txt file.
     *
     * @param inputTime String of input date or date AND time in MMM dd yyyy and hhmma.
     * @return Time in the converted format.
     */
    public static String unconvertDateTime(String inputTime) {
        assert inputTime != null : "Pika Pi, this is not valid!";
        String dateString = inputTime.substring(0, 11);
        String timeString = inputTime.substring(11);
        LocalDate date = LocalDate.parse(dateString.trim(), DATE_FORMAT);
        if (timeString.equals("")) {
            return date.toString();
        } else {
            LocalTime time = LocalTime.parse(timeString.trim(), TIME_FORMAT);
            return date.toString() + " " + time.toString();
        }
    }
}
