package catobot.command;

import catobot.exception.BotException;
import catobot.exception.EmptyCommandException;
import catobot.exception.InvalidCommandException;

/**
 * Represents the processor of raw input content into commands.
 */
public class Parser {
    /**
     * Parses the input into corresponding commands.
     *
     * @param content The full content from a line of input.
     * @return The corresponding command of the input.
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
        case FIND:
            return new FindCommand(content);
        case SORT:
            return new SortCommand(content);
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

    protected static String[] parseMultipleArgument(
            String content, CommandType type, String indicator, BotException exception) throws BotException {
        String name = type.getValue();
        // If the command is empty
        if (content.split(" ").length == 1) {
            throw new EmptyCommandException(name);
        }
        String rawInputs = content.split(name)[1].trim();

        // If the command does not have "/indicator"
        if (!rawInputs.contains(indicator)) {
            throw exception;
        }
        String[] details = rawInputs.split(indicator);

        // if there is no description or date
        if (details.length < 2) {
            throw exception;
        }
        return details;
    }

    protected static String parseSingleArgument(String content, CommandType type) {
        String name = type.getValue();
        return content.substring(name.length()).trim();
    }





}
