package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.ClearCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ReminderCommand;
import duke.commands.TodoCommand;

/**
 * Represents a parser which parses input from the user.
 */
public class Parser {

    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Parses the command from the user and returns the appropriate
     * Command based on the command, throws a DukeException if an
     * invalid command was entered.
     *
     * @param fullCommand The command entered by the user.
     * @return The corresponding Command.
     * @throws DukeException If invalid command entered by the user.
     */
    public Command parse(String fullCommand) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(fullCommand.trim());
        if (!matcher.matches()) {
            throw new DukeException("Invalid Command Entered");
        }
        String command = matcher.group("command");
        String arguments = matcher.group("arguments");
        switch (command) {
        case "exit":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo":
            return new TodoCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "reminder":
            return new ReminderCommand();
        case "clear":
            return new ClearCommand();
        default:
            throw new DukeException("Invalid Command Entered");
        }
    }
}
