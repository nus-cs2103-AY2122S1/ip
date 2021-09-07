package duke.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.logic.commands.Command;
import duke.logic.commands.DeadlineCommand;
import duke.logic.commands.DeleteCommand;
import duke.logic.commands.DoneCommand;
import duke.logic.commands.EventCommand;
import duke.logic.commands.ExitCommand;
import duke.logic.commands.FindCommand;
import duke.logic.commands.InvalidCommand;
import duke.logic.commands.ListCommand;
import duke.logic.commands.ToDoCommand;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param text The user input string.
     * @return The command based on the user input.
     */
    public Command parse(String text) {
        String[] tokens = text.trim().split("\\s+", 2); // split at the first white space
        Command command = parseTokens(tokens);
        return command;
    }

    /**
     * Categorizes the input into respective command types,
     * to prepare for further processing.
     *
     * @param tokens The tokenized input array.
     * @return The command based on the user input.
     */
    private Command parseTokens(String[] tokens) {
        switch (tokens[0]) {
        case "bye":
            return handleBye(tokens);
        case "list":
            return handleList(tokens);
        case "done":
            return handleDone(tokens);
        case "delete":
            return handleDelete(tokens);
        case "todo":
            return handleToDo(tokens);
        case "deadline":
            return handleDeadline(tokens);
        case "event":
            return handleEvent(tokens);
        case "find":
            return handleFind(tokens);
        default:
            return new InvalidCommand("Unknown command!");
        }
    }

    /**
     * Parses the tokens for an exit command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleBye(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length > 1) {
            return new InvalidCommand("Unknown command! Did you mean 'bye'?");
        }
        return new ExitCommand();
    }

    /**
     * Parses the tokens for a list tasks command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleList(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length > 1) {
            return new InvalidCommand("Unknown command! Did you mean 'list'?");
        }
        return new ListCommand();
    }

    /**
     * Parses the tokens for a mark task as done command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleDone(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length < 2) {
            return new InvalidCommand("Which task would you like to mark as done?");
        }
        try {
            int taskNo = Integer.parseInt(tokens[1]);
            return new DoneCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I don't see a task number! >.<");
        }
    }

    /**
     * Parses the tokens for a delete task command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleDelete(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length < 2) {
            return new InvalidCommand("Which task would you like to delete?");
        }
        try {
            int taskNo = Integer.parseInt(tokens[1]);
            return new DeleteCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I don't see a task number! >.<");
        }
    }

    /**
     * Parses the tokens for an add to-do task command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleToDo(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length < 2) {
            return new InvalidCommand("The description of a todo cannot be empty.");
        }
        return new ToDoCommand(tokens[1]);
    }

    /**
     * Parses the tokens for an add deadline task command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleDeadline(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length < 2) {
            return new InvalidCommand("The description of a deadline cannot be empty.");
        }
        String[] args = tokens[1].split(" /by ");
        if (args.length != 2) {
            return new InvalidCommand("Wrong deadline format! Requires <task name> /by <date>");
        }
        try {
            LocalDateTime by = LocalDateTime.parse(args[1], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            return new DeadlineCommand(args[0], by);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("I can't understand the date :( Please format it as DD-MM-YYYY hhmm (24hr time)");
        }
    }

    /**
     * Parses the tokens for an add event task command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleEvent(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length < 2) {
            return new InvalidCommand("The description of an event cannot be empty.");
        }
        String[] args = tokens[1].split(" /at ");
        if (args.length != 2) {
            return new InvalidCommand("Wrong event format! Requires <task name> /at <date>");
        }
        try {
            String[] times = args[1].split(" to ");
            LocalDateTime at = LocalDateTime.parse(times[0], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            LocalDateTime end = null;
            if (times.length > 1) {
                end = LocalDateTime.parse(times[1], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            }
            return new EventCommand(args[0], at, end);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("I cannot understand the date :( "
                    + "Please format it as DD-MM-YYYY hhmm[ to DD-MM-YYY hhmm] (Time in 24hr format)");
        }
    }

    /**
     * Parses tokens in the context of a find command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleFind(String[] tokens) {
        assert tokens.length > 0;
        if (tokens.length < 2) {
            return new InvalidCommand("What would you like to find? Separate your keywords with a space.");
        }
        String[] keywords = tokens[1].split("\\s+");
        return new FindCommand(keywords);
    }
}
