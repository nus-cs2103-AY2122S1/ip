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

    /** Strings for error messages */
    private static final String GENERAL_ERR_MSG = "Unknown command!";
    private static final String INVALID_BYE_ERR_MSG = GENERAL_ERR_MSG + " Did you mean 'bye'?";
    private static final String INVALID_LIST_ERR_MSG = GENERAL_ERR_MSG + " Did you mean 'list'?";
    private static final String INVALID_DONE_ERR_MSG = "Which task would you like to mark as done?";
    private static final String INVALID_DELETE_ERR_MSG = "Which task would you like to delete?";
    private static final String REQUIRE_TASK_NUMBER_ERR_MSG = "I don't see a task number! >.<";
    private static final String INVALID_TODO_ERR_MSG = "The description of a todo cannot be empty.";
    private static final String INVALID_DEADLINE_ERR_MSG = "Wrong format! " +
            "Requires \"deadline <task name> /by <DD-MM-YYYY hhmm> (24hr time)\"";
    private static final String INVALID_EVENT_ERR_MSG = "Wrong format! " +
            "Requires \"event <task name> /at <DD-MM-YYYY hhmm[ to DD-MM-YYY hhmm]> (24hr time)\"";
    private static final String INVALID_FIND_ERR_MSG = "What would you like to find? Separate keywords with a space.";

    /** Date format for deadline and event */
    private static final String DATE_FORMAT = "dd-MM-yyyy HHmm";

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
        if (tokens.length > 1) {
            return new InvalidCommand(INVALID_BYE_ERR_MSG);
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
        if (tokens.length > 1) {
            return new InvalidCommand(INVALID_LIST_ERR_MSG);
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
        if (tokens.length < 2) {
            return new InvalidCommand(INVALID_DONE_ERR_MSG);
        }
        try {
            int taskNo = Integer.parseInt(tokens[1]);
            return new DoneCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand(REQUIRE_TASK_NUMBER_ERR_MSG);
        }
    }

    /**
     * Parses the tokens for a delete task command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleDelete(String[] tokens) {
        if (tokens.length < 2) {
            return new InvalidCommand(INVALID_DELETE_ERR_MSG);
        }
        try {
            int taskNo = Integer.parseInt(tokens[1]);
            return new DeleteCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand(REQUIRE_TASK_NUMBER_ERR_MSG);
        }
    }

    /**
     * Parses the tokens for an add to-do task command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleToDo(String[] tokens) {
        if (tokens.length < 2) {
            return new InvalidCommand(INVALID_TODO_ERR_MSG);
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
        if (tokens.length < 2) {
            return new InvalidCommand(INVALID_DEADLINE_ERR_MSG);
        }
        String[] args = tokens[1].split(" /by ");
        if (args.length != 2) {
            return new InvalidCommand(INVALID_DEADLINE_ERR_MSG);
        }
        try {
            LocalDateTime by = LocalDateTime.parse(args[1], DateTimeFormatter.ofPattern(DATE_FORMAT));
            return new DeadlineCommand(args[0], by);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(INVALID_DEADLINE_ERR_MSG);
        }
    }

    /**
     * Parses the tokens for an add event task command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleEvent(String[] tokens) {
        if (tokens.length < 2) {
            return new InvalidCommand(INVALID_EVENT_ERR_MSG);
        }
        String[] args = tokens[1].split(" /at ");
        if (args.length != 2) {
            return new InvalidCommand(INVALID_EVENT_ERR_MSG);
        }
        try {
            String[] times = args[1].split(" to ");
            LocalDateTime at = LocalDateTime.parse(times[0], DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalDateTime end = null;
            if (times.length > 1) {
                end = LocalDateTime.parse(times[1], DateTimeFormatter.ofPattern(DATE_FORMAT));
            }
            return new EventCommand(args[0], at, end);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(INVALID_EVENT_ERR_MSG);
        }
    }

    /**
     * Parses tokens in the context of a find command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleFind(String[] tokens) {
        if (tokens.length < 2) {
            return new InvalidCommand(INVALID_FIND_ERR_MSG);
        }
        String[] keywords = tokens[1].split("\\s+");
        return new FindCommand(keywords);
    }

}
