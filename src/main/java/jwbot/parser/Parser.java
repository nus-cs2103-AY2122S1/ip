package jwbot.parser;

import jwbot.data.exception.JWBotException;
import jwbot.command.*;

public class Parser {

    public static Command parse(String input) throws JWBotException {
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
            throw new JWBotException("Sorry bro, I don't understand what you mean!");
        }
    }

}
