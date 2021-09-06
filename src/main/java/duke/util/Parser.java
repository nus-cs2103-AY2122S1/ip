package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.TodoCommand;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Utility class that contains static methods useful in parsing
 */
public final class Parser {
    private Parser() {

    }

    /**
     * Parses the string command and returns the correct command handler.
     *
     * @param fullCommand string input from the user
     * @return corresponding command handler
     * @throws InvalidInputException if command is not recognised.
     */
    public static Command parse(String[] fullCommand) throws InvalidInputException {
        assert fullCommand.length == 2;
        String command = fullCommand[0];
        String action = fullCommand[1];
        switch (command) {
        case "todo":
            return new TodoCommand(action);
        case "deadline":
            return new DeadlineCommand(action);
        case "event":
            return new EventCommand(action);
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(action);
        case "bye":
            return new ByeCommand();
        case "delete":
            return new DeleteCommand(action);
        case "find":
            return new FindCommand(action);
        case "tag":
            return new TagCommand(action);
        default:
            throw new InvalidInputException("This is an unknown command.");
        }
    }

    /**
     * Parses the string input into a LocalDateTime
     *
     * @param deadline String representation of the the date-time
     * @return LocalDateTime representation of the date-time
     * @throws InvalidInputException if input string cannot be parsed into a LocalDateTime
     */
    public static LocalDateTime parseDate(String deadline) throws InvalidInputException {
        try {
            return LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("We don't understand your date format.");
        }
    }

    /**
     * Splits one input into the command and the action
     *
     * @param input Input from the user
     * @return String array with length 2. First element is the command. Second element is the action
     */
    public static String[] parseInput(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 1) {
            return new String[] {splitInput[0], ""};
        }
        assert splitInput.length == 2;
        return splitInput;
    }

    /**
     * Generates the task from a string in the savefile
     *
     * @param input String input from the savefile
     * @return Task to be added to a task list
     * @throws InvalidInputException if task cannot be parsed
     */
    public static Task parseSaveFileLine(String input) throws InvalidInputException {
        String[] currTaskSplit = input.split("\\|\\|");
        Task newTask;
        switch (currTaskSplit[0]) {
        case "[T]":
            newTask = new Todo(currTaskSplit[3]);
            break;
        case "[E]":
            LocalDateTime eventTime = Parser.parseDate(currTaskSplit[4]);
            newTask = new Event(currTaskSplit[3], eventTime);
            break;
        case "[D]":
            LocalDateTime deadline = Parser.parseDate(currTaskSplit[4]);
            newTask = new Deadline(currTaskSplit[3], deadline);
            break;
        default:
            throw new InvalidInputException("Unrecognised task type detected in save file.");
        }
        if (!currTaskSplit[1].equals("null")) {
            newTask.setTag(currTaskSplit[1]);
        }
        if (currTaskSplit[2].equals("true")) {
            newTask.complete();
        }
        return newTask;
    }
}
