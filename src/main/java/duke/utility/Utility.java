package duke.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A Utility class to handle useful functions
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class Utility {

    /**
     * Convert a string in the supported format into a LocalDateTime.
     * @param string The string to convert.
     */
    public static LocalDateTime stringToDate(String string) {
        String[] details = string.split(" ", 2);
        LocalDate localDate;
        LocalDateTime localDateTime = null;
        String date = details[0];
        String time = details.length == 2 ? details[1] : null;
        boolean isDateInFormat = date.matches("\\d{1,2}/\\d{1,2}/\\d{4}");
        if (isDateInFormat) {
            String[] dateDetails = date.split("/");
            localDate = LocalDate.of(
                    Integer.parseInt(dateDetails[2]),
                    Integer.parseInt(dateDetails[1]),
                    Integer.parseInt(dateDetails[0]));
            if (time != null) {
                localDateTime = localDate.atTime(
                        Integer.parseInt(time.substring(0, 2)),
                        Integer.parseInt(time.substring(2, 4)));
            } else {
                localDateTime = localDate.atStartOfDay();
            }
        }
        return localDateTime;
    }
}
