package catobot.command;

import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;

public class Parser {
    /**
     * Responds to the request.
     *
     * @param content The full request message from a line of input.
     * @throws BotException if the request is invalid.
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
