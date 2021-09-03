package lifeline.parser;

import static lifeline.util.ErrorString.ERROR_INVALID_COMMAND;

import lifeline.command.Command;
import lifeline.exception.LifelineException;

/**
 * The class Parser parses the input from the user.
 */
public class Parser {

    /**
     * Returns parsed command from user input.
     *
     * @param command Command read from user.
     * @return Command to be executed based on user input.
     * @throws LifelineException if command is invalid.
     */
    public static Command parse(String command) throws LifelineException {
        String cleanAndLowerString = command.trim().split("\\s", 2)[0].toLowerCase();
        for (Command c : Command.values()) {
            if (c.hasCommand(cleanAndLowerString)) {
                return c;
            }
        }
        throw new LifelineException(ERROR_INVALID_COMMAND);
    }
}
