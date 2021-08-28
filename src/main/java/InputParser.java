import commands.*;
import exceptions.DukeException;

/**
 * This is an InputParser class, which translates user input
 * into commands.
 */
public class InputParser {
    private final static String DELIMITER = "\\s+";
    private final static int COMMAND_INDEX = 0;

    /**
     * Returns the command specified by the user.
     * @param userInput The input string entered by the user.
     * @return The command specified by the user.
     * @throws DukeException
     */
    public Command getCommand(String userInput) throws DukeException {
        String trimmedUserInput = userInput.trim();
        String[] words = trimmedUserInput.split(DELIMITER);
        String commandKeyword = words[COMMAND_INDEX].toLowerCase();
        if (userInput.contains(StorageParser.DELIMITER)) {
            throw new DukeException("OOPS!!! Please avoid using the symbol \"" + Storage.DELIMITER + "\".");
        }
        switch (commandKeyword) {
        case FindCommand.KEYWORD:
            return new FindCommand(trimmedUserInput);
        case DeleteCommand.KEYWORD:
            return new DeleteCommand(trimmedUserInput);
        case AddEventCommand.KEYWORD:
            return new AddEventCommand(trimmedUserInput);
        case AddDeadlineCommand.KEYWORD:
            return new AddDeadlineCommand(trimmedUserInput);
        case AddToDoCommand.KEYWORD:
            return new AddToDoCommand(trimmedUserInput);
        case MarkDoneCommand.KEYWORD:
            return new MarkDoneCommand(trimmedUserInput);
        case ListCommand.KEYWORD:
            return new ListCommand();
        default:
            // User gave an invalid command
            return new InvalidCommand();
        }
    }
}
