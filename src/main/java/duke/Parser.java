package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser is responsible for making sense of the user inputs.
 * This includes identifying the different components for each command and converting them to actual tasks
 * and commands that Duke can use.
 */
public class Parser {

    /**
     * Parses a todo task input.
     *
     * @param input The user's specified todo task.
     * @return The todo task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parserForTodoTasks(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        String task = input.substring(5);
        return new Todo(task);
    }

    /**
     * Parses a deadline task input by identifying the actual task, the date and time.
     *
     * @param input The user's specified deadline task.
     * @return The deadline task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
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

    /**
     * Parses an event task input by identifying the actual task, the date and time.
     *
     * @param input The user's specified event task.
     * @return The event task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
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
     * @param text string format of the date and time.
     * @return The converted date and time.
     */
    public static LocalDateTime textToDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(text, formatter);
    }

    /**
     * Converts from date and time format to string format.
     *
     * @param dateAndTime date and time.
     * @return The string version of the date.
     */
    public static String dateToText(LocalDateTime dateAndTime) {
        return dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
    }

}
