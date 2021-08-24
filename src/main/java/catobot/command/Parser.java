package catobot.command;

import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;

/**
 * Represents the processor of raw input content into commands.
 */
public class Parser {

    /**
     * Parses the input into corresponding commands.
     *
     * @param content The full content from a line of input.
     * @throws BotException If the request is invalid.
     */
    public static Command parse(String content) throws BotException {
        CommandType commandType = CommandType.find(content);
        switch (commandType) {
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(content);
        case DELETE:
            return new DeleteCommand(content);
        case TODO:
            return new TodoCommand(content);
        case DEADLINE:
            return new DeadlineCommand(content);
        case EVENT:
            return new EventCommand(content);
        case CLOSE:
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }

}
