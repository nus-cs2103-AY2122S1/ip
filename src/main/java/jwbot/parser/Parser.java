package jwbot.parser;

import jwbot.command.AddDeadlineCommand;
import jwbot.command.AddEventCommand;
import jwbot.command.AddTodoCommand;
import jwbot.command.Command;
import jwbot.command.DeleteCommand;
import jwbot.command.DoneCommand;
import jwbot.command.ExitCommand;
import jwbot.command.FindCommand;
import jwbot.command.ListCommand;
import jwbot.data.exception.JwBotException;


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
    public static Command parse(String input) throws JwBotException {
        assert input != null : "input is null";
        if (input.equals("bye")) {
            return new ExitCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("done ")) {
            return new DoneCommand(input);
        } else if (input.startsWith("find ")) {
            return new FindCommand(input);
        } else if (input.startsWith("deadline ")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("todo ")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("event ")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else {
            throw new JwBotException("Sorry bro, I don't understand what you mean!");
        }
    }

}
