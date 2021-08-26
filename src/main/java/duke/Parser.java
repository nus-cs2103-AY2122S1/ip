package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * duke.Parser class to parse the commands entered during run time
 */

public class Parser {

    /**
     * Splits the input line into description and time for the deadline
     * or event
     * @param string    the input line
     * @return          array containing the description and time info
     */
    public static String[] splitDescriptionAndTime(String string) {
        return string.split("/");
    }

    /**
     * Extracts the description after splitting using splitDescriptionAndTime
     * @param description_and_time  array after splitting the input line
     * @return                      the description of the Task
     */
    public static String getDescription(String[] description_and_time) {
        return description_and_time[0].split(" ", 2)[1];
    }

    /**
     * Extracts the description after splitting using splitDescriptionAndTime
     * @param description_and_time  array after splitting the input line
     * @return                      the description of the Task
     */
    public static String getTime (String[] description_and_time) {
        LocalDate localDate = LocalDate.parse(description_and_time[1].split(" ", 2)[1]);
        String time = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return time;
    }
}
