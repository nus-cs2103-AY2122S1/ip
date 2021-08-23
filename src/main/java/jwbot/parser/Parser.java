package jwbot.parser;

import jwbot.data.exception.JWBotException;
import jwbot.command.*;

/**
 * Parser class that parses the user command
 *
 * @author  Yim Jaewon
 */
public class Parser {

    /**
     * Parse the user input command to an adequate type of command object.
     *
     * @param input the command entered by the user
     * @return a parsed Command object created based on the user command
     */
    public static Command parse(String input) throws JWBotException {
        if (input.equals("bye")) {
            return new ExitCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("done ")) {
            return new DoneCommand(input);
        } else if (input.startsWith("deadline ")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("todo ")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("event ")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else {
            throw new JWBotException("Sorry bro, I don't understand what you mean!");
        }
    }

}
