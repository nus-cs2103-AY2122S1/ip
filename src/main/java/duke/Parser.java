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

        setDoneStatusToStoredTask(isTaskDone, todoTask);
        setTagToStoredTask(storedTodoTask, todoTask);

        return todoTask;
    }

    /**
     * Parses a deadline task input by identifying the actual task, the date and time.
     *
     * @param deadlineTask The user's specified deadline task.
     * @return The deadline task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parseDeadlineTasks(String deadlineTask) throws EmptyDescriptionException,
            EmptyDateTimeException {
        if (deadlineTask.length() <= 9) {
            throw new EmptyDescriptionException("error");
        }

        if (!deadlineTask.contains("/by")) {
            throw new EmptyDateTimeException("error");
        }

        assert (deadlineTask.length() > 10);

        int charIndex = deadlineTask.indexOf("/");

        if (charIndex == 9) {
            throw new EmptyDescriptionException("error");
        }

        int byIndex = charIndex + 4;

        String by = deadlineTask.substring(byIndex);
        String task = deadlineTask.substring(9, charIndex - 1);

        if (task.isBlank()) {
            throw new EmptyDescriptionException("error");
        }

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

        setDoneStatusToStoredTask(isTaskDone, deadlineTask);
        setTagToStoredTask(storedDeadlineTask, deadlineTask);

        return deadlineTask;
    }

    /**
     * Parses an event task input by identifying the actual task, the date and time.
     *
     * @param eventTask The user's specified event task.
     * @return The event task corresponding to the user's string input.
     * @throws EmptyDescriptionException If the description of the task is empty.
     */
    public static Task parseEventTasks(String eventTask) throws EmptyDescriptionException,
            EmptyDateTimeException {
        if (eventTask.length() <= 6) {
            throw new EmptyDescriptionException("error");
        }

        if (!eventTask.contains("/at")) {
            throw new EmptyDateTimeException("error");
        }

        assert (eventTask.length() > 7);

        int charIndex = eventTask.indexOf("/");

        if (charIndex == 6) {
            throw new EmptyDescriptionException("error");
        }

        int atIndex = charIndex + 4;

        String at = eventTask.substring(atIndex);
        String task = eventTask.substring(6, charIndex - 1);

        if (task.isBlank()) {
            throw new EmptyDescriptionException("error");
        }

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

        Task eventTask = new Event(eventTaskDescription, at);

        setDoneStatusToStoredTask(isTaskDone, eventTask);
        setTagToStoredTask(storedEventTask, eventTask);

        return eventTask;
    }

    /**
     * Sets the status of whether the stored task was marked at done or not by the user.
     *
     * @param isTaskDone True or false string value as set by the user for the stored task.
     * @param task The task loaded based on the stored task.
     */
    public static void setDoneStatusToStoredTask(String isTaskDone, Task task) {
        if (isTaskDone.equals("true")) {
            task.markAsDone();
        }
    }

    /**
     * Sets the tag for the stored task as tagged by the user.
     *
     * @param storedTask The stored task.
     * @param task The task loaded based on the stored task.
     */
    public static void setTagToStoredTask(String storedTask, Task task) {
        if (storedTask.contains("#")) {
            int indexOfTag = storedTask.indexOf("#");
            String tag = storedTask.substring(indexOfTag);
            task.setTag(tag);
        }
    }

    /**
     * Parses a find task input by identifying the keyword in the input.
     *
     * @param findTask The user's find command.
     * @return The keyword in the command to be found in the list of tasks.
     * @throws EmptyDescriptionException If the keyword is missing.
     */
    public static String parseFindTasks(String findTask) throws EmptyDescriptionException {
        if (findTask.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        assert (findTask.length() > 4);

        return findTask.substring(5);
    }

    /**
     * Parses a done task input by identifying the index specified by the user.
     *
     * @param doneTask The user's done command.
     * @return The index of the task to be marked done.
     * @throws EmptyDescriptionException If the index is not specified.
     */
    public static int parseDoneTasks(String doneTask) throws EmptyDescriptionException {
        if (doneTask.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        assert (doneTask.length() > 4);

        char indexOfTask = doneTask.charAt(5);

        return Integer.parseInt(String.valueOf(indexOfTask));
    }

    /**
     * Parses a delete task input by identifying the index specified by the user.
     *
     * @param deleteTask The user's delete command.
     * @return The index of the task to be deleted.
     * @throws EmptyDescriptionException If the index is not specified.
     */
    public static int parseDeleteTasks(String deleteTask) throws EmptyDescriptionException {
        if (deleteTask.length() == 6) {
            throw new EmptyDescriptionException("error");
        }

        assert (deleteTask.length() > 6);

        char taskIndex = deleteTask.charAt(7);

        return Integer.parseInt(String.valueOf(taskIndex));
    }

    /**
     * Parses a tag task input by identifying the index specified by the user.
     *
     * @param tagTask The user's tag command.
     * @return The index of the task to be tagged.
     * @throws EmptyDescriptionException If the index is not specified.
     */
    public static int parseTagTasksForIndexOfTask(String tagTask) throws EmptyDescriptionException {
        if (tagTask.length() < 4) {
            throw new EmptyDescriptionException("error");
        }

        assert (tagTask.length() > 4);

        char indexOfTask = tagTask.charAt(4);

        return Integer.parseInt(String.valueOf(indexOfTask));
    }

    /**
     * Parses a tag task input by identifying the tag.
     *
     * @param tagTask The user's tag command.
     * @return The tag for the task
     * @throws EmptyDescriptionException If the tag is not specified.
     */
    public static String parseTagTasksForTag(String tagTask) throws EmptyDescriptionException {
        if (tagTask.length() < 7) {
            throw new EmptyDescriptionException("error");
        }

        assert (tagTask.length() > 7);

        return tagTask.substring(6);
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
