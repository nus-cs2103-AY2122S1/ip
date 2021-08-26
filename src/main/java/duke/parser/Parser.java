package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;

public class Parser {
    public Command parse(String text) {
        String[] tokens = text.split(" ", 2);   // split at the first white space
        Command command = parseTokens(tokens);
        return command;
    }

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
    
    private Command handleBye(String[] tokens) {
        if (tokens.length > 1) {
            return new InvalidCommand("Unknown command! Did you mean 'bye'?");
        }
        return new ExitCommand();
    }

    private Command handleList(String[] tokens) {
        if (tokens.length > 1) {
            return new InvalidCommand("Unknown command! Did you mean 'list'?");
        }
        return new ListCommand();
    }

    private Command handleDone(String[] tokens) {
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

    private Command handleDelete(String[] tokens) {
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

    private Command handleToDo(String[] tokens) {
        if (tokens.length < 2) {
            return new InvalidCommand("The description of a todo cannot be empty.");
        }
        return new ToDoCommand(tokens[1]);
    }

    private Command handleDeadline(String[] tokens) {
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
            return new InvalidCommand("I cannot understand the date :( Please format it as DD-MM-YYYY hhmm (24hr time)");
        }
    }

    private Command handleEvent(String[] tokens) {
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
            return new InvalidCommand("I cannot understand the date :( " +
                    "Please format it as DD-MM-YYYY hhmm[ to DD-MM-YYY hhmm] (Time in 24hr format)");
        }
    }

    /**
     * Parses tokens in the context of a find command.
     *
     * @param tokens The tokenized input array.
     * @return The prepared command.
     */
    private Command handleFind(String[] tokens) {
        if (tokens.length != 2) {
            return new InvalidCommand("What would you like to find? I can search for exactly 1 keyword.");
        }
        return new FindCommand(tokens[1]);
    }
}
