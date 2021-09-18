package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser is responsible for making sense of the user inputs, as well as any other parsing required.
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
    public static Task parseTodoTasks(String todoTask) throws EmptyDescriptionException {
        if (todoTask.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        assert(todoTask.length() > 4);

        String task = todoTask.substring(5);

        return new Todo(task);
    }

    /**
     * Parses any stored (already existing) deadline tasks from the storage file.
     *
     * @param storedTodoTask The pre-existing todo task.
     * @return A todo task to be loaded to the UI when the user restarts Duke.
     */
    public static Task parseStoredTodoTasks(String storedTodoTask) {
        int isDoneIndex = storedTodoTask.indexOf("/");
        String isTaskDone = storedTodoTask.substring(isDoneIndex + 2);
        Task todoTask = new Todo(storedTodoTask.substring(5, isDoneIndex));
        if (isTaskDone.equals("true")) {
            todoTask.markAsDone();
        }
        return todoTask;
    }

    /**
     * Parses a deadline task input by identifying the actual task, the date and time.
     *
     * @param deadlineTask The user's specified deadline task.
     * @return The deadline task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parseDeadlineTasks(String deadlineTask) throws EmptyDescriptionException {
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
     * Parses any stored (already existing) deadline tasks from the storage file.
     *
     * @param storedDeadlineTask The pre-existing deadline task.
     * @return A deadline task to be loaded to the UI when the user restarts Duke.
     */
    public static Task parseStoredDeadlineTasks(String storedDeadlineTask) {
        int isDoneIndex = storedDeadlineTask.indexOf("/");
        int charIndex = storedDeadlineTask.indexOf("|");
        int byIndex = charIndex + 2;
        String by = storedDeadlineTask.substring(byIndex, isDoneIndex);
        String deadlineTaskDescription = storedDeadlineTask.substring(9, charIndex - 1);
        String isTaskDone = storedDeadlineTask.substring(isDoneIndex + 2);

        Task deadlineTask = new Deadline(deadlineTaskDescription, by);

        if (isTaskDone.equals("true")) {
            deadlineTask.markAsDone();
        }
        return deadlineTask;
    }

    /**
     * Parses an event task input by identifying the actual task, the date and time.
     *
     * @param eventTask The user's specified event task.
     * @return The event task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parseEventTasks(String eventTask) throws EmptyDescriptionException {
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
     * Parses any stored (already existing) event tasks from the storage file.
     *
     * @param storedEventTask The pre-existing event task.
     * @return An event task to be loaded to the UI when the user restarts Duke.
     */
    public static Task parseStoredEventTasks(String storedEventTask) {
        int isDoneIndex = storedEventTask.indexOf("/");
        int charIndex = storedEventTask.indexOf("|");
        int atIndex = charIndex + 2;
        String at = storedEventTask.substring(atIndex, isDoneIndex);
        String eventTaskDescription = storedEventTask.substring(6, charIndex - 1);

        String isTaskDone = storedEventTask.substring(isDoneIndex + 2);

        Task eventTask = new Deadline(eventTaskDescription, at);

        if (isTaskDone.equals("true")) {
            eventTask.markAsDone();
        }
        return eventTask;
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
