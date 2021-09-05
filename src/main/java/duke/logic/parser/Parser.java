package duke.logic.parser;

import duke.exception.DukeInvalidCommandException;
import duke.logic.command.*;
import duke.logic.tasks.Deadline;
import duke.logic.tasks.Event;
import duke.logic.tasks.TaskList;
import duke.logic.tasks.ToDo;

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

    private String[] parseInput(String input) {
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

    private String handleList(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length >= 2) {
            throw new DukeInvalidCommandException("OOPS!!! Do you mean 'list' ?");
        } else {
            return new ListCommand().executeCommand(taskList);
        }
    }

    private String handleDone(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! Which task do you want to mark as done?");
        }
        int taskIndex = parseTaskIndex(parsedInput[1]);
        // where to properly handle this?
        if (taskList.getSize() == 0) {
            throw new DukeInvalidCommandException("OOPS!!! The task list is currently empty.");
        }
        return new DoneCommand(taskIndex).executeCommand(taskList);
    }

    private String handleDeadline(String[] parsedInput) throws DukeInvalidCommandException {
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
            return new DeadlineCommand(new Deadline(parsedArguments[0], date)).executeCommand(taskList);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidCommandException("OOPS!!! Wrong time format. Correct format should be yyyy-mm-dd");
        }
    }

    private String handleEvent(String[] parsedInput) throws DukeInvalidCommandException {
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
            return new EventCommand(new Event(parsedArguments[0], date)).executeCommand(taskList);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidCommandException("OOPS!!! Wrong time format. Correct format should be yyyy-mm-dd");
        }
    }

    private String handleTodo(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! The description of a todo task cannot be empty.");
        }
        return new ToDoCommand(new ToDo(parsedInput[1])).executeCommand(taskList);
    }

    private String handleDelete(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! Which task do you want to delete?");
        }
        int taskIndex = parseTaskIndex(parsedInput[1]);

        if (taskList.getSize() == 0) {
            throw new DukeInvalidCommandException("OOPS!!! The task list is currently empty.");
        }
        return new DeleteCommand(taskIndex).executeCommand(taskList);
    }

    private String handleFind(String[] parsedInput) throws DukeInvalidCommandException {
        if (parsedInput.length < 2) {
            throw new DukeInvalidCommandException("OOPS!!! Type in the keyword you want to search");
        }
        return new FindCommand(parsedInput[1]).executeCommand(taskList);
    }

    public String invokeCommand(String input) throws DukeInvalidCommandException {
        String[] parsedInput = parseInput(input);
        switch (parsedInput[0]) {
        case "list":
            return handleList(parsedInput);
        case "done":
            return handleDone(parsedInput);
        case "deadline":
            return handleDeadline(parsedInput);
        case "event":
            return handleEvent(parsedInput);
        case "todo":
            return handleTodo(parsedInput);
        case "delete":
            return handleDelete(parsedInput);
        case "find":
            return handleFind(parsedInput);
        default:
            throw new DukeInvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
