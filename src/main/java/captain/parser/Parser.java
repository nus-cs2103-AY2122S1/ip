package captain.parser;

import captain.DukeException;
import captain.command.Command;

/**
 * Represents a Parser that parses the user's input into a Command.
 * @param <T>
 */
public interface Parser<T extends Command> {

    /**
     * Parses the user input into a command.
     * @param userInput The input from user.
     * @return A Command of type T
     * @throws DukeException Throws ParseException when the user input format is invalid.
     */
    T parse(String userInput) throws DukeException;
}
