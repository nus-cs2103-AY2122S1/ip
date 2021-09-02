package duke.io;

import duke.commands.Command;
import duke.commands.CommandTypes;
import duke.exceptions.AuguryException;
import duke.exceptions.InvalidActionException;
import duke.exceptions.UnknownCommandException;

/**
 * The {@code Parser} class handles command parsing.
 */
public class Parser {

    public Parser() { }

    /**
     * Converts the provided user {@code String input} into a {@code Command}.
     *
     * @param input {@code String} of user input.
     * @return {@code String} which corresponds to the command that the user entered.
     */
    public Command parse(String input) throws AuguryException {
        assert input.equals(input.toLowerCase());

        if (input.equals("bye") || input.equals("exit") || input.equals("quit")) {
            return Command.of(CommandTypes.QUIT);
        } else if (input.equals("list") || input.equals("ls")) {
            return Command.of(CommandTypes.LIST);
        } else if (input.startsWith("done")) {
            String indexes = input.substring(4).trim();
            checkIndexesAreValid(indexes);
            return Command.of(CommandTypes.MARKDONE, indexes);
        } else if (input.startsWith("delete")) {
            String indexes = input.substring(6).trim();
            checkIndexesAreValid(indexes);
            return Command.of(CommandTypes.DELETE, indexes);
        } else if (input.startsWith("find")) {
            String queries = input.replace("find ", "");
            return Command.of(CommandTypes.FIND, queries);
        } else if (input.startsWith("event") || input.startsWith("deadline") || input.startsWith("todo")) {
            return Command.of(CommandTypes.MAKE, input);
        } else {
            throw new UnknownCommandException("Unknown command entered.");
        }
    }

    private void checkIndexesAreValid(String indexes) throws InvalidActionException {
        if (indexes.length() == 0) {
            throw new InvalidActionException("Please enter a valid task number.");
        }

        String[] xs = indexes.split(",");
        for (String s : xs) {
            if (!isPositiveInteger(s.trim())) {
                throw new InvalidActionException("Please enter a valid task number.");
            }
        }
    }

    private boolean isPositiveInteger(String s) {
        try {
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
