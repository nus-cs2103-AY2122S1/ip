package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class converts the input string of date and time into
 * a better, understandable date and time format.
 */
public class DateTimeConverter {

    /**
     * Empty constructor for DateTimeConverter.
     */
    public DateTimeConverter() {}

    /**
     * Returns a string containing a newly formatted date and time.
     *
     * @param datetime String containing the date and time to be
     *                 changed.
     * @return String containing a newly formatted date and time.
     */
    public String convertDateAndTime(String datetime) {
        final int noon = 1200;
        String time = datetime.substring(datetime.length() - 4);
        String hour;
        String minute;

        int actualTime = Integer.parseInt(time);
        if (actualTime >= noon) {
            // If time is between 12:00 and 12:59PM
            if ((actualTime - noon) / 100 < 1) {
                hour = "12";
            } else {
                hour = String.valueOf(Math.round((actualTime - noon) / 100));
            }
            minute = String.valueOf(actualTime % 100);
            // If time is of XX:00 PM format
            if (minute.equals("0")) {
                minute += "0";
            }
            time = hour + ":" + minute + "PM";
        } else {
            // If time is between 00:00 and 00:59AM
            if (actualTime / 100 < 1) {
                hour = "12";
            } else {
                hour = String.valueOf(Math.round(actualTime / 100));
            }
            minute = String.valueOf(actualTime % 100);
            // If time is of XX:00 AM format
            if (minute.equals("0")) {
                minute += "0";
            }
            time = hour + ":" + minute + "AM";
        }
        String date = datetime.substring(0, datetime.length() - 5);
        LocalDate d1 = LocalDate.parse(date);
        return d1.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + time;
    }
}
