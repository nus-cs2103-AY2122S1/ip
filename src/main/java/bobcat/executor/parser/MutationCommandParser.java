package bobcat.executor.parser;

import bobcat.exception.CommandArityException;
import bobcat.exception.InvalidArgumentException;

/**
 * Implements a <code>CommandParser</code> which process taskMutation commands. Such commands include "done" and
 * "delete". In particular, it checks for the number of arguments corresponding to the provided command, as well as
 * type of the commandArgs i.e. must be cast-able to an Integer.
 */
public class MutationCommandParser implements CommandParser {
    /**
     * Process taskMutation commands.
     * @param command The given taskMutation command
     * @param commandArgs Arguments to the taskMutation command given
     * @return Array of strings, where the 0th index is occupied by command, followed by the arguments to the command
     * @throws CommandArityException May be thrown if number of elements in commandArgs is not appropriate relative to
     * given command
     * @throws InvalidArgumentException May be thrown if elements of commandArgs are not castable to integer
     */
    @Override
    public String[] parse(String command, String[] commandArgs) {
        if (commandArgs.length != 2) {
            throw new CommandArityException(commandArgs.length > 1
                    ? "You've entered too many indices. Multiple " + command + " is unsupported right now!"
                    : "Index needs to be specified!");
        }
        try {
            return new String[]{command, String.valueOf(Integer.parseInt(commandArgs[1]) - 1)};
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("You've entered inappropriate argument/s to the command: " + command);
        }
    }
}
