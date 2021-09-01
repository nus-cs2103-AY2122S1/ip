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
     * Returns string response for each user input
     *
     * @param fullCommand Command entered by user
     * @param taskList List of tasks
     * @param ui User interaction object
     * @param storage Storage for tasks
     * @return String representation of duke's response
     * @throws DukeException If user input does not meet requirement
     */
    public static String parse(String fullCommand, TaskList taskList,
                                Ui ui, Storage storage) throws DukeException {
        String[] command = fullCommand.split(" ", 2);
        String commandWord = command[0].trim();

        switch (commandWord) {
        case "todo":
            return parseTodo(taskList, command);
        case "event":
            return parseEvent(taskList, command);
        case "deadline":
            return parseDeadline(taskList, command);
        case "bye":
            return parseBye(storage, ui, taskList);
        case "list":
            return parseList(taskList);
        case "delete":
            return parseDelete(taskList, command);
        case "done":
            return parseDone(taskList, command);
        case "find":
            return parseFind(taskList, command);
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
     * Returns string response when user enters list.
     *
     * @param taskList List of tasks
     * @return String representation of duke's response for list input
     */
    public static String parseList(TaskList taskList) {
        return taskList.printTasksInList();
    }

    /**
     * Returns string response when user enters find.
     *
     * @param taskList List of tasks
     * @param details Array of details about the find command entered by user
     * @return String representation for when user enters find
     * @throws DukeException If user didn't enter keyword for find
     */
    public static String parseFind(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new DukeException("Please enter a keyword to find matching tasks...");
        }

        String keyword = details[1].trim();
        return taskList.findMatchingTasks(keyword);
    }

    /**
     * Returns string response for when user enters event.
     *
     * @param taskList List of tasks
     * @param details Array of details about event entered by user
     * @return String representation of duke's response when user enters event
     * @throws DukeException If details doesn't contain description/date or date is of wrong format
     */
    public static String parseEvent(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new NoDescriptionAndTimeException("event");
        } else {
            String[] eventDetail = parseDescriptionAndTime(details,
                    "/at", "event");
            return taskList.addEventToList(eventDetail[0].trim(),
                    eventDetail[1].trim());
        }
    }

    /**
     * Returns string response when user enters deadline.
     *
     * @param taskList List of tasks
     * @param details Array of details about deadline entered by uer
     * @return String representation of duke's response when user enters deadline
     * @throws DukeException If details doesn't contain description/date or date is of wrong format
     */
    public static String parseDeadline(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new NoDescriptionAndTimeException("deadline");
        } else {
            String[] eventDetail = parseDescriptionAndTime(details,
                    "/by", "deadline");
            return taskList.addDeadlineToList(eventDetail[0].trim(),
                    eventDetail[1].trim());
        }
    }

    /**
     * Return string response when user enters bye.
     *
     * @param storage Storage for tasks
     * @param ui User interaction object
     * @param taskList List of tasks
     * @return String representation of duke's response when user enters bye
     * @throws DukeException If folder/file for storage doesn't exist
     */
    public static String parseBye(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        storage.save(taskList);
        return ui.showBye();
    }

    /**
     * Returns string response when user enters delete.
     *
     * @param taskList List of tasks.
     * @param userInput Array of delete command
     * @return String representation of duke's response when user deletes task
     * @throws DukeException If task number for deletion is not entered or out of range
     */
    public static String parseDelete(TaskList taskList, String[] userInput) throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }

        int deleteNumber = Integer.parseInt(userInput[1].trim()) - 1;
        if (deleteNumber < 0 || deleteNumber > taskList.listLength()-1) {
            throw new InvalidTaskDeletionException();
        } else {
            return taskList.deleteFromList(deleteNumber);
        }
    }

    /**
     * Returns string response for done command entered.
     *
     * @param taskList List of tasks
     * @param userInput Array for done command
     * @return String response for done command entered.
     * @throws DukeException If task number is not entered or is out of range
     */
    public static String parseDone(TaskList taskList, String[] userInput) throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }

        int doneNumber = Integer.parseInt(userInput[1].trim()) - 1;
        if (doneNumber < 0 || doneNumber > taskList.listLength()-1) {
            throw new InvalidTaskDoneException();
        } else {
            return taskList.setTaskAsDone(doneNumber);
        }
    }

    /**
     * Returns string response for todo command entered.
     *
     * @param taskList List of tasks
     * @param userInput Array of todo command for parsing
     * @return String response for todo command entered
     * @throws DukeException If no description is entered
     */
    public static String parseTodo(TaskList taskList, String[] userInput) throws DukeException {
        if (userInput.length == 1) {
            throw new NoDescriptionException("todo");
        } else {
            return taskList.addTodoToList(userInput[1].trim());
        }
    }

}
