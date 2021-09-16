package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.task.command.ByeCommand;
import duke.task.command.Command;
import duke.task.command.DeadlineCommand;
import duke.task.command.DeleteCommand;
import duke.task.command.DoneCommand;
import duke.task.command.EventCommand;
import duke.task.command.FindCommand;
import duke.task.command.HelpCommand;
import duke.task.command.ListCommand;
import duke.task.command.TodoCommand;
import duke.task.command.UndoCommand;

/**
 * Reads and converts user input to bhutu-understandable inputs.
 */
public class Parser {

    private Command parseDeadline(String input) throws DukeException {
        String date;
        String[] inputWords = input.split(" /by ");
        if (inputWords.length < 2) {
            throw new DukeException("Please provide both description and date. Use '/by'. "
                    + "(eg. deadline submit project /by 2021-08-24)");
        }
        StringBuilder description = new StringBuilder();
        String[] parseDescription = inputWords[0].split("\\s+");
        for (int i = 1; i < parseDescription.length; i++) {
            String s = parseDescription[i];
            description.append(s).append(" ");
        }
        date = parseDate(inputWords[1]);
        return new DeadlineCommand(Duke.getLatestState(), description.toString(), date);
    }

    private Command parseEvent(String input) throws DukeException {
        String[] inputWords = input.split(" /at ");
        if (inputWords.length < 2) {
            throw new DukeException("Please provide both description and time. Use '/at'.\n"
                    + "(eg. event fix hair /at 1pm)");
        }
        StringBuilder description = new StringBuilder();
        String[] parseDescription = inputWords[0].split("\\s+");
        for (int i = 1; i < parseDescription.length; i++) {
            String s = parseDescription[i];
            description.append(s).append(" ");
        }
        return new EventCommand(Duke.getLatestState(), description.toString(), inputWords[1]);
    }

    private Command parseTodo(String input) throws DukeException {
        String[] inputWords = input.split("\\s+");
        if (inputWords.length < 2) {
            throw new DukeException("Please specify the task you want to do");
        } else {
            StringBuilder description = new StringBuilder();
            for (int i = 1; i < inputWords.length; i++) {
                String s = inputWords[i];
                description.append(s).append(" ");
            }
            return new TodoCommand(Duke.getLatestState(), description.toString());
        }
    }

    private Command parseDone(String input) throws DukeException {
        int index;
        String[] inputWords = input.split("\\s+");
        if (inputWords.length < 2) {
            throw new DukeException("Please specify which task you have done");
        } else if (inputWords.length != 2) {
            throw new DukeException("'done' command requires exactly 1 argument. (eg. done 12)");
        }
        try {
            index = Integer.parseInt(inputWords[1]);
        } catch (Exception e) {
            throw new DukeException("'done' command requires an integer as number. (eg. done 12)");
        }
        return new DoneCommand(Duke.getLatestState(), index);
    }

    private Command parseList(String[] input) throws DukeException {
        if (input.length != 1) {
            throw new DukeException("'list' command doesn't require any arguments.");
        } else {
            return new ListCommand(Duke.getLatestState());
        }
    }

    private Command parseBye(String[] input) throws DukeException {
        if (input.length != 1) {
            throw new DukeException("'bye' command doesn't require any arguments.");
        } else {
            return new ByeCommand();
        }
    }

    private Command parseDelete(String input) throws DukeException {
        int index;
        String[] inputWords = input.split("\\s+");
        if (inputWords.length < 2) {
            throw new DukeException("Please specify which task you want to delete");
        } else if (inputWords.length != 2) {
            throw new DukeException("'delete' command requires exactly 1 argument. (eg. delete 12)");
        }
        try {
            index = Integer.parseInt(inputWords[1]);
        } catch (Exception e) {
            throw new DukeException("'delete' command requires an integer as number. (eg. delete 12)");
        }
        return new DeleteCommand(Duke.getLatestState(), index);
    }

    /**
     * Converts the user input string into meaningful commands.
     *
     * @param input the user input string.
     * @return the meaningful commands.
     */
    public Command compileInput(String input) throws DukeException {
        String output;
        String[] inputWords;
        inputWords = input.split("\\s+");

        if (inputWords.length == 0) {
            output = "Sorry, I don't understand empty statements.";
            throw new DukeException(output);
        }
        String commandEntered = inputWords[0];
        assert commandEntered != null : "Command is NULL";

        switch (commandEntered) {
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "todo":
            return parseTodo(input);
        case "done":
            return parseDone(input);
        case "list":
            return parseList(inputWords);
        case "bye":
            return parseBye(inputWords);
        case "delete":
            return parseDelete(input);
        case "find":
            return new FindCommand(Duke.getLatestState(), inputWords[1]);
        case "undo":
            return new UndoCommand();
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("I don't recognise this command\n"
                    + "Try 'list', 'todo', 'event', 'deadline', 'done', 'bye', 'help' or 'undo'.");
        }
    }

    /**
     * Parses a raw date string as input into a valid date and time string.
     *
     * @param input The raw date string.
     * @return A string valid as a date.
     * @throws DukeException Thrown if the input is an invalid date.
     */
    private String parseDate(String input) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException error) {
            throw new DukeException("Please enter a valid date in this format 'YYYY-MM-dd' "
                    + "(eg. 2021-10-07)");
        }
    }
}
