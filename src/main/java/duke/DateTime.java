package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    /**
     * Parses a string into a date.
     *
     * @param dateString The string containing a date.
     */
    public static LocalDateTime parse(String dateString) throws Exception {
        try {
            return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss"));
            } catch (Exception err) {
                throw new Exception("Invalid datetime: " + dateString + "\n"
                        + "Please use format: YYYY-MM-DD HH:MM:SS");
            }
        }
    }

    public static String stringify(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss"));
    }
}
