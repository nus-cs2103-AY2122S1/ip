package bribot.parser;

import bribot.command.Command;
import bribot.command.ExceptionCommand;
import bribot.command.ExitCommand;
import bribot.command.ListCommand;
import bribot.command.SortCommand;
import bribot.command.UnknownCommand;
import bribot.exception.DukeException;
import bribot.parser.commandparser.DeadlineCommandParser;
import bribot.parser.commandparser.DeleteCommandParser;
import bribot.parser.commandparser.DoneCommandParser;
import bribot.parser.commandparser.EventCommandParser;
import bribot.parser.commandparser.FindCommandParser;
import bribot.parser.commandparser.TodoCommandParser;

/**
 * Represents the parser that parses user input to make sense of the given input and produce
 * the appropriate command.
 */
public class Parser {
    /**
     * Takes in a String input and parses it to produce the appropriate command
     * @param input the user input.
     * @return the appropriate command.
     */
    public static Command parse(String input) {
        try {
            String[] words = input.split(" ", 2);
            String keyWord = words[0];

            switch (keyWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "sort":
                return new SortCommand();
            case "done":
                return new DoneCommandParser().parse(input);
            case "delete":
                return new DeleteCommandParser().parse(input);
            case "todo":
                return new TodoCommandParser().parse(input);
            case "deadline":
                return new DeadlineCommandParser().parse(input);
            case "event":
                return new EventCommandParser().parse(input);
            case "find":
                return new FindCommandParser().parse(input);
            default:
                return new UnknownCommand();
            }
        } catch (DukeException e) {
            return new ExceptionCommand(e);
        }
    }
}
