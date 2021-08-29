import commands.*;
import exceptions.MorganException;

/**
 * This is an CommandParser class, which translates user input
 * into commands.
 */
public class CommandParser {
    private final static String DELIMITER = "\\s+";
    private final static int COMMAND_INDEX = 0;
    private final static String DELIMITER_FOUND_ERROR_MESSAGE = "Please "
            + "avoid using the symbol \"" + Storage.DELIMITER + "\".";

    /**
     * Returns the command specified by the user.
     * @param userInput The input string entered by the user.
     * @return The command specified by the user.
     * @throws MorganException
     */
    public Command getCommand(String userInput) throws MorganException {
        String trimmedUserInput = userInput.trim();
        String[] words = trimmedUserInput.split(DELIMITER);
        String commandKeyword = words[COMMAND_INDEX].toLowerCase();
        if (userInput.contains(TaskParser.DELIMITER)) {
            throw new MorganException(DELIMITER_FOUND_ERROR_MESSAGE);
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
