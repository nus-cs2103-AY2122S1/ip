package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static LocalDateTime parse(String str) throws Exception {
        try {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss"));
            } catch (Exception err) {
                throw new Exception("Invalid datetime: " + str + "\n" + "Please use format: YYYY-MM-DD HH:MM:SS");
            }
        }
    }

    public static String stringify(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss"));
    }
}
