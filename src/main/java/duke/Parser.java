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
     * @param todoTask The user's specified todo task.
     * @return The todo task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parserForTodoTasks(String todoTask) throws EmptyDescriptionException {
        if (todoTask.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        assert(todoTask.length() > 4);

        String task = todoTask.substring(5);
        return new Todo(task);
    }

    /**
     * Parses a deadline task input by identifying the actual task, the date and time.
     *
     * @param deadlineTask The user's specified deadline task.
     * @return The deadline task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parserForDeadlineTasks(String deadlineTask) throws EmptyDescriptionException {
        if (deadlineTask.length() == 8) {
            throw new EmptyDescriptionException("error" );
        }

        assert (deadlineTask.length() > 8);

        int charIndex = deadlineTask.indexOf("/" );
        int byIndex = charIndex + 4;
        String by = deadlineTask.substring(byIndex);
        String task = deadlineTask.substring(9, charIndex - 1);
        return new Deadline(task, by);
    }

    /**
     * Parses an event task input by identifying the actual task, the date and time.
     *
     * @param eventTask The user's specified event task.
     * @return The event task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parserForEventTasks(String eventTask) throws EmptyDescriptionException {
        if (eventTask.length() == 5) {
            throw new EmptyDescriptionException("error");
        }

        assert (eventTask.length() > 5);

        int charIndex = eventTask.indexOf("/");
        int atIndex = charIndex + 4;

        String at = eventTask.substring(atIndex);
        String task = eventTask.substring(6, charIndex - 1);

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
