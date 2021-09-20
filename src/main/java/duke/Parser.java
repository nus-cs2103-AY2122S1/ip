package duke;

import duke.command.Add;
import duke.command.Command;
import duke.command.Delete;
import duke.command.Done;
import duke.command.Exit;
import duke.command.Find;
import duke.command.List;
import duke.dukeexception.EmptyDescriptionException;
import duke.dukeexception.IllegalCommandException;

/**
 * Represents a class that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Returns the appropriate Command that a given string contains.
     *
     * @param input The given input string from the user.
     * @return The Command that the string contains.
     * @throws IllegalCommandException If the string did not contain a valid command.
     * @throws EmptyDescriptionException If the string contains todo but no following description.
     */
    public static Command parse(String input) throws IllegalCommandException, EmptyDescriptionException {
        assert(input.length() != 0);
        if (input.equals("bye")) {
            return new Exit();
        } else if (input.equals("list")) {
            return new List();
        } else if (input.startsWith("done")) {
            int value = Character.getNumericValue(input.charAt(5));
            assert(value > 0);
            return new Done(value);
        } else if (input.startsWith("delete")) {
            int value = Character.getNumericValue(input.charAt(7));
            assert(value > 0);
            return new Delete(value);
        } else if (input.startsWith("find")) {
            String searchTerm = input.substring(4);
            return new Find(searchTerm);
        } else {
            try {
                return new Add(input);
            } catch (IllegalCommandException | EmptyDescriptionException e) {
                throw e;
            }
        }
    }
}
