package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDeletionException;
import duke.exception.InvalidTaskDoneException;
import duke.exception.NoCommandException;
import duke.exception.NoDescriptionAndTimeException;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;
import duke.task.TaskList;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Encapsulates the parsing of user inputs.
 */
public class Parser {

    /**
     * Returns false if user input is "bye".
     * Otherwise, true is returned.
     *
     * @param fullCommand Command entered by user
     * @param taskList List of tasks
     * @param ui User interaction object
     * @param storage Storage for tasks
     * @return Boolean value for whether duke should exit the program
     * @throws DukeException If user input does not meet requirement
     */
    public static boolean parse(String fullCommand, TaskList taskList,
                                Ui ui, Storage storage) throws DukeException {

        String[] command = fullCommand.split(" ", 2);
        String commandWord = command[0].trim();

        switch (commandWord) {
        case "todo":
            return hasParsedTodo(taskList, command);
        case "event":
            return hasParsedEvent(taskList, command);
        case "deadline":
            return hasParsedDeadline(taskList, command);
        case "bye":
            return hasParsedBye(storage, ui, taskList);
        case "list":
            return hasParsedList(taskList);
        case "delete":
            return hasParsedDelete(taskList, command);
        case "done":
            return hasParsedDone(taskList, command);
        case "find":
            return hasParsedFind(taskList, command);
        default:
            throw new NoCommandException();
        }
    }

    /**
     * Returns array of string that stores description and date of task.
     *
     * @param userInput Array that stores task type and details of task
     * @param regex Regex to split the details of task into description and date.
     * @param taskType Type of task
     * @return Array of string containing description and date respectively
     * @throws DukeException if userInput doesn't contain description or date
     */
    public static String[] parseDescriptionAndTime(String[] userInput,String regex,
                                          String taskType) throws DukeException {

        String[] next = userInput[1].trim().split(regex);

        if (next[0].trim().length() == 0) {
            throw new NoDescriptionException(taskType);
        } else if (next.length < 2) {
            throw new NoTimeException(taskType);
        }

        return next;
    }

    /**
     * Returns date formatted as LocalDate.
     *
     * @param dateTime Date entered by user
     * @return A date of the LocalDate format
     * @throws ParseException If dateTime is not of required format
     */
    public static LocalDate parseDate(String dateTime) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(dateTime, formatter);
        return date;
    }


    /**
     * Returns false for duke to continue running.
     *
     * @param taskList List of tasks
     * @return Boolean value for duke to continue running
     */
    public static boolean hasParsedList(TaskList taskList) {
        taskList.printTasksInList();
        return false;
    }

    /**
     * Returns false for duke to continue running.
     *
     * @param taskList List of tasks
     * @param details Array of details about the find command entered by user
     * @return Boolean value to keep duke running
     * @throws DukeException If user didn't enter keyword for find
     */
    public static boolean hasParsedFind(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new DukeException("Please enter a keyword to find matching tasks...");
        }

        String keyword = details[1].trim();
        taskList.findMatchingTasks(keyword);

        return false;
    }

    /**
     * Returns false for duke to continue running.
     *
     * @param taskList List of tasks
     * @param details Array of details about event entered by user
     * @return Boolean value for duke to continue running
     * @throws DukeException If details doesn't contain description/date or date is of wrong format
     */
    public static boolean hasParsedEvent(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new NoDescriptionAndTimeException("event");
        } else {
            String[] eventDetail = parseDescriptionAndTime(details,
                    "/at", "event");
            taskList.addEventToList(eventDetail[0].trim(),
                    eventDetail[1].trim());
        }

        return false;
    }

    /**
     * Returns false for duke to continue running.
     *
     * @param taskList List of tasks
     * @param details Array of details about deadline entered by uer
     * @return Boolean value for duke to continue running
     * @throws DukeException If details doesn't contain description/date or date is of wrong format
     */
    public static boolean hasParsedDeadline(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new NoDescriptionAndTimeException("deadline");
        } else {
            String[] eventDetail = parseDescriptionAndTime(details,
                    "/by", "deadline");
            taskList.addDeadlineToList(eventDetail[0].trim(),
                    eventDetail[1].trim());
        }
        return false;
    }

    /**
     * Return true so stop duke program.
     *
     * @param storage Storage for tasks
     * @param ui User interaction object
     * @param taskList List of tasks
     * @return Boolean value to stop duke from running
     * @throws DukeException If folder/file for storage doesn't exist
     */
    public static boolean hasParsedBye(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        storage.save(taskList);
        ui.showBye();
        return true;
    }

    /**
     * Returns false for duke to continue running.
     *
     * @param taskList List of tasks.
     * @param userInput Array of delete command
     * @return Boolean value for duke to continue running
     * @throws DukeException If task number for deletion is not entered or out of range
     */
    public static boolean hasParsedDelete(TaskList taskList, String[] userInput) throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }

        int deleteNumber = Integer.parseInt(userInput[1].trim()) - 1;
        if (deleteNumber < 0 || deleteNumber > taskList.listLength()-1) {
            throw new InvalidTaskDeletionException();
        } else {
            taskList.deleteFromList(deleteNumber);
        }

        return false;
    }

    /**
     * Returns false for duke to continue running.
     *
     * @param taskList List of tasks
     * @param userInput Array for done command
     * @return Boolean value for duke to continue running
     * @throws DukeException If task number is not entered or is out of range
     */
    public static boolean hasParsedDone(TaskList taskList, String[] userInput) throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }

        int doneNumber = Integer.parseInt(userInput[1].trim()) - 1;
        if (doneNumber < 0 || doneNumber > taskList.listLength()-1) {
            throw new InvalidTaskDoneException();
        } else {
            taskList.setTaskAsDone(doneNumber);
        }

        return false;
    }

    /**
     * Returns false for duke to continue running.
     *
     * @param taskList List of tasks
     * @param userInput Array of todo command for parsing
     * @return Boolean value for duke to continue running
     * @throws DukeException If no description is entered
     */
    public static boolean hasParsedTodo(TaskList taskList, String[] userInput) throws DukeException {
        if (userInput.length == 1) {
            throw new NoDescriptionException("todo");
        } else {
            taskList.addTodoToList(userInput[1].trim());
        }

        return false;
    }

}
