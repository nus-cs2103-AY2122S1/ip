package parser;

import exception.CommandArityException;
import exception.InvalidArgumentException;

public class MutationCommandParser implements CommandParser {
    @Override
    public String[] parse(String command, String[] commandArgs) {
        if (commandArgs.length != 2) {
            throw new CommandArityException(commandArgs.length > 1
                    ? "☹ OOPS!!! You've entered too many indices. Multiple " + command + " is unsupported right now!"
                    : "☹ OOPS!!! Index needs to be specified!");
        }
        try {
            return new String[]{command, String.valueOf(Integer.parseInt(commandArgs[1]) - 1)};
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("☹ OOPS!!! You've entered inappropriate argument/s to the command: " + command);
        }
    }
}
