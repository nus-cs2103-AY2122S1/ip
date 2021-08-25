package biscuit.parser;


import biscuit.commands.AddCommand;
import biscuit.commands.Command;
import biscuit.commands.DeleteCommand;
import biscuit.commands.DoneCommand;
import biscuit.commands.ExitCommand;
import biscuit.commands.FindCommand;
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
        String[] processedInputs = userInput.trim().split("\\s+", 2);
        switch (processedInputs[0]) {
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new AddCommand(processedInputs);
        case "done":
            return new DoneCommand(processedInputs);
        case "delete":
            return new DeleteCommand(processedInputs);
        case "list":
            return new ListCommand(processedInputs);
        case "find":
            return new FindCommand(processedInputs);
        case "bye":
            return new ExitCommand(processedInputs);
        default:
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! I'm sorry, but I don't know what that means...");
        }
    }

}
