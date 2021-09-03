package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.enums.Commands;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.UnknownCommandException;

/** Utility class that handles parsing of user input to program command. */
public class Parser {
    /**
     * Returns a Commands enum type from the user's full command input by regex splitting the command string and
     * returning the first word.
     *
     * @param fullCommand The full command input from the user.
     * @return Commands enum type.
     */
    private static Commands commandParser(String fullCommand) {
        // Trims and extracts the first word of the full command by splitting using the space character.
        return Commands.valueOfLabel(fullCommand.trim().split(" ")[0]);
    }

    /**
     * Returns a string containing the content of the full command input by removing the command keyword and returning
     * the remainder of the string using regex.
     *
     * @param fullCommand The full command input from the user.
     * @return The content of the command as string.
     */
    private static String contentParser(String fullCommand) {
        String[] token = fullCommand.trim().split(" ", 2);
        String content = "";
        if (token.length < 2) { // Guard clause
            return content;
        }
        content = token[1];
        return content;
    }

    /**
     * Checks for empty user input.
     *
     * @param fullCommand The full command input from the user.
     * @throws EmptyCommandException If the command is empty or consists of only whitespace.
     */
    private static void checkBlankCommand(String fullCommand) throws EmptyCommandException {
        if (fullCommand.trim().length() < 1) {
            throw new EmptyCommandException();
        }
    }

    /**
     * Parses the user's full input to Command object.
     *
     * @param fullCommand The full command input from the user.
     * @return A Command object.
     * @throws DukeException If an exception is found.
     */
    public static Command parse(String fullCommand) throws DukeException {
        checkBlankCommand(fullCommand); // Guard clause
        Command command;
        String content = contentParser(fullCommand);
        switch (commandParser(fullCommand)) {
        case EXIT:
            command = ExitCommand.of(content);
            break;
        case LIST:
            command = ListCommand.of(content);
            break;
        case ADD:
            command = AddCommand.of(content);
            break;
        case DONE:
            command = DoneCommand.of(content);
            break;
        case DELETE:
            command = DeleteCommand.of(content);
            break;
        case FIND:
            command = FindCommand.of(content);
            break;
        case SORT:
            command = SortCommand.of(content);
            break;
        default:
            throw new UnknownCommandException(fullCommand);
        }
        return command;
    }
}
