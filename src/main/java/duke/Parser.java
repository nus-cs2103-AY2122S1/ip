package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Task parserForTodoTasks(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        String task = input.substring(5);
        return new Todo(task);
    }

    public static Task parserForDeadlineTasks(String input) throws EmptyDescriptionException {
        if (input.length() == 8) {
            throw new EmptyDescriptionException("error" );
        }
        int charIndex = input.indexOf("/" );
        int byIndex = charIndex + 4;
        String by = input.substring(byIndex);
        String task = input.substring(9, charIndex - 1);
        return new Deadline(task, by);
    }

    public static Task parserForEventTasks(String input) throws EmptyDescriptionException {
        if (input.length() == 5) {
            throw new EmptyDescriptionException("error");
        }
        int charIndex = input.indexOf("/");
        int atIndex = charIndex + 4;

        String at = input.substring(atIndex);
        String task = input.substring(6, charIndex - 1);

        return new Event(task, at);
    }


    /**
     * Converts from string format to date format.
     *
     * @param text string format of the date.
     * @return date.
     */
    public static LocalDateTime textToDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(text, formatter);
        return date;
    }

    /**
     * Converts from date format to string format.
     *
     * @param date date.
     * @return string text version of the date.
     */
    public static String dateToText(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
    }

}
