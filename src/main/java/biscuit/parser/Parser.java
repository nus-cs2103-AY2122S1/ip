package biscuit.parser;


import biscuit.commands.AddCommand;
import biscuit.commands.Command;
import biscuit.commands.DeleteCommand;
import biscuit.commands.DoneCommand;
import biscuit.commands.ExitCommand;
import biscuit.commands.ListCommand;
import biscuit.exceptions.BiscuitException;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Constructs Parser class.
     */
    private Parser() {
    }

    /**
     * Parses user input to get command.
     *
     * @param userInput Input by user.
     * @return Command.
     * @throws BiscuitException Invalid command by user.
     */
    public static Command parse(String userInput) throws BiscuitException {
        String[] processedInput = userInput.trim().split("\\s+", 2);
        switch (processedInput[0]) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(processedInput);
        case "done":
            return new DoneCommand(processedInput);
        case "delete":
            return new DeleteCommand(processedInput);
        case "list":
            return new ListCommand(processedInput);
        case "bye":
            return new ExitCommand(processedInput);
        default:
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! I'm sorry, but I don't know what that means...");
        }
    }

}
