package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Helps to parse user input time string
 */
public class TimeHandler {

    /**
     * Returns a formatted data and time string.
     *
     * @param timeString string input from user
     * @return a formatted data and time string
     */
    public static String parse(String timeString) {
        String[] s = timeString.trim().split("\\s+");
        String formattedDate = "";
        String formattedTime = "";
        for (String s2 : s) {
            if (s2.chars().filter(c->c=='/').count() == 2) {
                formattedDate = parseDate(s2);
            } else if (s2.length() == 4 && s2.matches("\\d{4}")) {
                formattedTime = " " + parseTime(s2);
            }
        }
        return formattedDate + formattedTime;
    }

    public static String parseDate(String dateString) {
        // parse date from format from d/m/y to d mmm yyyy
        String[] date = dateString.split("/");
        assert date.length == 3;
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        LocalDate localDate = LocalDate.of(year, month, day);
        return localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public static String parseTime(String timeString) {
        // parse date from format from 24 hr format to 12 hr format
        // e.g. 2030 -> 08:30 PM
        assert timeString.length() == 4;
        assert timeString.matches("\\d{4}");
        int hour = Integer.parseInt(timeString.substring(0,2));
        String minute = timeString.substring(2);
        String meridiem = (hour > 11) ? "PM" : "AM";
        hour = (hour + 11) % 12 + 1;
        return hour + ":" + minute + " " + meridiem;
    }

}
