package bobcat.executor.parser;

import java.util.Arrays;

import bobcat.exception.CommandArityException;

/**
 * Implements a <code>CommandParser</code> which process taskCreation commands. Such commands include "todo",
 * "event", and "bye". In particular it checks for the number of arguments corresponding to the provided command.
 */
public class CreationCommandParser implements CommandParser {
    /**
     * Process taskCreation commands.
     * @param command The given taskCreation command
     * @param commandArgs Arguments to the taskCreation command given
     * @return Array of strings, where the 0th index is occupied by command, followed by the arguments to the command
     * @throws CommandArityException May throw if number of elements in commandArgs is not appropriate relative to
     * given command
     */
    @Override
    public String[] parse(String command, String[] commandArgs) {
        if (command.equals("todo")) {
            String commandArg = String.join(" ",
                    Arrays.copyOfRange(commandArgs, 1, commandArgs.length));
            if (commandArg.length() <= 0) {
                throw new CommandArityException("The description of a " + command + " cannot be empty.");
            }
            return new String[]{command, commandArg};
        } else {
            String args = String.join(" ",
                    Arrays.copyOfRange(commandArgs, 1, commandArgs.length));
            String[] argArr = args.split("\\s/" + (command.equals("deadline") ? "by" : "at"));
            if (argArr[0].trim().length() <= 0) {
                throw new CommandArityException("The description of a " + command + " cannot be empty.");
            } else if (argArr.length == 1) {
                throw new CommandArityException("The date of a " + command + " cannot be empty.");
            }
            return new String[]{command, argArr[0].trim(), argArr[1].trim()};
        }
    }
}
