package duke;

import duke.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        case "bye":
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
        default:
            throw new DukeException("Invalid Command Entered");
        }
    }
}
