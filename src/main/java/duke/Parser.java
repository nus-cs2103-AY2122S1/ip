package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Input parsing and displaying output to the user.
 */
public class Parser {
    private final TaskList taskList;

    /**
     * Constructor for the class.
     */
    public Parser() {
        this.taskList = new TaskList();
    }

    public String[] parseInput(String input) {
        return input.trim().split(" ", 2);
    }

    private int parseTaskIndex(String input) throws DukeInvalidCommandException {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException("OOPS!!! The task number you type in is not a number.");
        }
        if (parsedNumber < 0 || parsedNumber > taskList.getSize()) {
            throw new DukeInvalidCommandException("OOPS!!! The task number should be between 0 and "
                    + taskList.getSize() + ".");
        }
        return parsedNumber;
    }

    private void handleList(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length >= 2) {
            throw new DukeInvalidCommandException("OOPS!!! Do you mean 'list' ?");
        } else {
            Ui.displayWithLines(taskList.toString());
        }
    }

    private void handleDone(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! Which task do you want to mark as done?");
        }
        int taskIndex = parseTaskIndex(parsedInput[1]);
        // where to properly handle this?
        if (taskList.getSize() == 0) {
            throw new DukeInvalidCommandException("OOPS!!! The task list is currently empty.");
        }
        Ui.displayWithLines(taskList.markTaskAsDone(taskIndex));
    }

    private void handleDeadline(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] parsedArguments = parsedInput[1].split(" /by ");
        if (parsedArguments.length != 2) {
            throw new DukeInvalidCommandException("OOPS!!! Wrong format. \n" +
                    "\t Correct format should be: deadline <deadline_description> /by <deadline_time>");
        }
        try {
            LocalDate date = LocalDate.parse(parsedArguments[1]);
            Ui.displayWithLines(taskList.addTask(new Deadline(parsedArguments[0], date)));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidCommandException("OOPS!!! Wrong time format. Correct format should be yyyy-mm-dd");
        }
    }

    private void handleEvent(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] parsedArguments = parsedInput[1].split(" /at ");
        if (parsedArguments.length != 2) {
            throw new DukeInvalidCommandException("OOPS!!! Wrong format. \n" +
                    "\t Correct format should be: event <event_description> /at <event_time>");
        }
        try {
            LocalDate date = LocalDate.parse(parsedArguments[1]);
            Ui.displayWithLines(taskList.addTask(new Event(parsedArguments[0], date)));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidCommandException("OOPS!!! Wrong time format. Correct format should be yyyy-mm-dd");
        }
    }

    private void handleTodo(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! The description of a todo task cannot be empty.");
        }
        Ui.displayWithLines(taskList.addTask(new ToDo(parsedInput[1])));
    }

    private void handleDelete(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! Which task do you want to delete?");
        }
        int taskIndex = parseTaskIndex(parsedInput[1]);

        if (taskList.getSize() == 0) {
            throw new DukeInvalidCommandException("OOPS!!! The task list is currently empty.");
        }
        Ui.displayWithLines(taskList.delete(taskIndex));
    }

    private void handleFind(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! Type in the keyword you want to search");
        }
        Ui.displayWithLines(taskList.findTask(parsedInput[1]));
    }
    public void invokeCommand(String input) throws DukeInvalidCommandException {
        String[] parsedInput = parseInput(input);
        switch (parsedInput[0]) {
        case "list":
            handleList(parsedInput);
            break;
        case "done":
            handleDone(parsedInput);
            break;
        case "deadline":
            handleDeadline(parsedInput);
            break;
        case "event":
            handleEvent(parsedInput);
            break;
        case "todo":
            handleTodo(parsedInput);
            break;
        case "delete":
            handleDelete(parsedInput);
            break;
        case "find":
            handleFind(parsedInput);
            break;
        default:
            throw new DukeInvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
