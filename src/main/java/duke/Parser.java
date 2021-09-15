package duke;

import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeUnknownCommandException;

/**
 * Represents a parser for the user input.
 */
public class Parser {
    /**
     * Parse user's input and return the command word for that input.
     *
     * @param input user input from System.in
     * @return command word of the input
     * @throws DukeUnknownCommandException if user provided an unknown command which is not included in the enum
     */
    public static CommandType parseCmd(String input) throws DukeUnknownCommandException {
        String command = input.split(" ", 2)[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeUnknownCommandException(command);
        }
    }


    /**
     * Parse user's input and return the argument for that input.
     *
     * @param input user input from System.in
     * @return argument fields of the input
     * @throws DukeMissingArgumentException if the command requires argument(s) and it is missing
     */
    public static String parseArgs(String input) throws DukeMissingArgumentException {
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }
}
