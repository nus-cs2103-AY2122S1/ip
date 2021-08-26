package duke.util;

import duke.exception.DukeException;
import duke.exception.NoCommandException;
import duke.exception.NoDescriptionAndTimeException;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;
import duke.exception.InvalidTaskDeletionException;
import duke.exception.InvalidTaskDoneException;
import duke.task.Task;
import duke.task.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Parser {
    public static boolean parse(String fullCommand, TaskList taskList,
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
            return hasParsedFind(taskList, command);
        default:
            throw new NoCommandException();
        }
    }

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

    public static LocalDate parseDate(String dateTime) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(dateTime, formatter);
        return date;
    }

    public static boolean parseList(TaskList taskList) {
        taskList.printTasksInList();
        return false;
    }

    public static boolean hasParsedFind(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new DukeException("Please enter a keyword to find matching tasks...");
        }

        String keyword = details[1].trim();
        taskList.findMatchingTasks(keyword);

        return false;
    }

    public static boolean parseEvent(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new NoDescriptionAndTimeException("event");
        }
        String[] eventDetail = parseDescriptionAndTime(details,
                "/at", "event");

        taskList.addEventToList(eventDetail[0].trim(),
                eventDetail[1].trim());
        return false;
    }

    public static boolean parseDeadline(TaskList taskList, String[] details) throws DukeException {
        if (details.length == 1) {
            throw new NoDescriptionAndTimeException("deadline");
        }
        String[] eventDetail = parseDescriptionAndTime(details,
                "/by", "deadline");
        taskList.addDeadlineToList(eventDetail[0].trim(),
                eventDetail[1].trim());
        return false;
    }

    public static boolean parseBye(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        storage.saveTaskList(taskList);
        ui.showBye();
        return true;
    }

    public static boolean parseDelete(TaskList taskList, String[] userInput)
            throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }
        int deleteNumber = Integer.parseInt(userInput[1].trim()) - 1;
        if (deleteNumber < 0 || deleteNumber > taskList.listLength()-1) {
            throw new InvalidTaskDeletionException();
        }
        taskList.deleteFromList(deleteNumber);
        return false;
    }

    public static boolean parseDone(TaskList taskList, String[] userInput)
            throws DukeException {
        if (userInput.length == 1) {
            throw new DukeException("No task number entered!");
        }
        int doneNumber = Integer.parseInt(userInput[1].trim()) - 1;
        if (doneNumber < 0 || doneNumber > taskList.listLength()-1) {
            throw new InvalidTaskDoneException();
        }
        taskList.setTaskAsDone(doneNumber);
        return false;
    }

    public static boolean parseTodo(TaskList taskList, String[] userInput)
            throws DukeException {
        if (userInput.length == 1) {
            throw new NoDescriptionException("todo");
        }
        taskList.addTodoToList(userInput[1].trim());
        return false;
    }

}
