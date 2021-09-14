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
        case "event":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "todo":
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
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!!\n"
                    + "I'm sorry, but I don't know what that means...");
        }
    }

}
