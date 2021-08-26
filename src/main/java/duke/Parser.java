package duke;

import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeUnknownCommandException;

import java.lang.reflect.Array;

public class Parser {
    public static CommandType parseCmd(String input) throws DukeUnknownCommandException {
        String command = input.split(" ", 2)[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeUnknownCommandException(command);
        }
    }

    public static String parseArgs(String input) throws DukeMissingArgumentException {
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }
}
