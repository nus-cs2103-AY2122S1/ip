package bribot.parser.commandparser;

import bribot.command.Command;
import bribot.exception.DukeException;

/**
 * Represents a command parser that is able to pass the pararmeters
 */
public interface CommandParser<T extends Command> {

    T parse(String userInput) throws DukeException;

    /**
     * Returns an error message as a string if the input is invalid else sets arguments and returns null
     */
    String argumentSetter() ;
}
