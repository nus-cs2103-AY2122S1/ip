package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {

    /**
     * Takes in a String date and returns its corresponding LocalDate object.
     *
     * @param date String date in format DD/MM/YYYY, with 1-2 digits from Day and Month.
     * @return LocalDate object with the corresponding day, month and year.
     * @throws DateTimeParseException Thrown if date passed is an invalid one.
     */
    public static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Checks if a time is in an invalid format
     *
     * @param time Time to check
     * @return False if the time is valid, true if invalid (outside the range of 0000-2359)
     */
    public static boolean checkInvalidTime(String time) {
        if (time.equals("")) {
            return false;
        }

        int timeInt = Integer.parseInt(time);
        return (timeInt >= 2400 || timeInt < 0);
    }
}
