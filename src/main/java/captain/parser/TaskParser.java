package captain.parser;

import static captain.Commands.INVALID;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import captain.Commands;
import captain.DukeException;
import captain.command.*;

/**
 * Represents a parser to make sense of user input.
 *
 * @author Adam Ho
 */
public class TaskParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<userCommand>\\S+)(?<taskDescription>.*)");

    /**
     * Makes sense of the user input.
     * @param fullCommand The input from user.
     * @return A Command representation of the user's input.
     * @throws DukeException Throws a DukeException if the user's input does not comply with current features.
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(fullCommand.trim());

        if (!matcher.matches()) {
            throw new DukeException("Invalid command format!");
        }

        String userCommand = matcher.group("userCommand");
        String taskDescription = matcher.group("taskDescription");
        Commands command = getUserCommand(userCommand);

        switch (command) {
        case LIST:
            return new ListCommand();
        case ADD:
            return new AddCommandParser().parse(taskDescription);
        case DONE:
            return new DoneCommandParser().parse(taskDescription);
        case DELETE:
            return new DeleteCommandParser().parse(taskDescription);
        case FIND:
            return new FindCommandParser().parse(taskDescription);
        case CLEAR:
            return new ClearCommand();
        case SORT:
            return new SortCommand();
        case EXIT:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    public static Commands getUserCommand(String userCommand) {
        for (Commands c : Commands.values()) {
            if (c.userCommand.equalsIgnoreCase(userCommand)) {
                return c;
            }
        }
        return INVALID;
    }
}
