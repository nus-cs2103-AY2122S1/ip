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


public class Parser {

    public static boolean parse(
            String fullCommand, TaskList taskList, Ui ui, Storage storage) throws DukeException {

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
        default:
            throw new NoCommandException();
        }
    }

    public static String[] parseDescriptionAndTime(
            String[] userInput,String regex, String taskType) throws DukeException {

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

    public static boolean hasParsedList(TaskList taskList) {
        taskList.printTasksInList();
        return false;
    }

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

    public static boolean hasParsedBye(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        storage.save(taskList);
        ui.showBye();
        return true;
    }

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

    public static boolean hasParsedTodo(TaskList taskList, String[] userInput) throws DukeException {
        if (userInput.length == 1) {
            throw new NoDescriptionException("todo");
        } else {
            taskList.addTodoToList(userInput[1].trim());
        }

        return false;
    }

}
