package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * duke.Parser class to parse the commands entered during run time
 */

public class Parser {

    /**
     * Splits the input line into description and time for the deadline
     * or event.
     *
     * @param string the input line
     * @return array containing the description and time info
     */
    public static String[] splitDescriptionAndTime(String string) {
        assert string != "" : "string to split cannot be empty";
        return string.split("/");
    }

    /**
     * Extracts the description after splitting using splitDescriptionAndTime.
     *
     * @param description_and_time array after splitting the input line
     * @return the description of the Task
     */
    public static String getDescription(String[] description_and_time) {
        assert description_and_time != null : "Tasks description and time cannot be null";
        return description_and_time[0].split(" ", 2)[1];
    }

    /**
     * Extracts the description after splitting using splitDescriptionAndTime.
     *
     * @param description_and_time array after splitting the input line
     * @return the description of the Task
     */

    public static String getTime(String[] description_and_time) {
        assert description_and_time != null : "Tasks description and time cannot be null";
        LocalDate localDate = LocalDate.parse(description_and_time[1].split(" ", 2)[1]);
        String time = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return time;
    }

    /**
     * Extracts the new input from the input.
     *
     * @param description the input entered by the user
     * @return
     */
    public static String getNewUpdatedTime(String description) {
        int timeIndex = description.indexOf("t/");
        String extractedTime = description.substring(timeIndex + 2);
        LocalDate localDate = LocalDate.parse(extractedTime);
        String time = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return time;
    }

    /**
     * Extracts the new description from the input.
     *
     * @param description the input entered by the user
     * @return
     */
    public static String getNewUpdatedDescription(String description) {
        int descriptionIndex = description.indexOf("d/");
        int timeIndex = description.indexOf("t/");
        String extractedDescription = "";
        if (timeIndex != -1) {
            extractedDescription = description.substring(descriptionIndex + 2, timeIndex);
        } else {
            extractedDescription = description.substring(descriptionIndex + 2);
        }
        return extractedDescription;
    }
}
