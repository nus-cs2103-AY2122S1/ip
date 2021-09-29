package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Integer parseDelete(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }
    public static Integer parseDone(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }
    public static String parseEvent(String input) {
        return input.split("event ")[1];
    }
    public static String parseTodo(String input) {
        return input.split("todo ")[1];
    }
    public static String parseDeadline(String input) {
        return input.split("deadline ")[1];
    }
    public static String parseTiming(String timing) {
        String formattedDateTime = "";
        String date= timing.split(" ")[0];
        String time = timing.split(" ")[1];
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        LocalDate formattedDate = LocalDate.parse(date);
        LocalTime formattedTime = LocalTime.of(hour, minute);
        formattedDateTime += formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " ";
        formattedDateTime += formattedTime.format(DateTimeFormatter.ofPattern("ha"));
        return formattedDateTime;
    }
}
