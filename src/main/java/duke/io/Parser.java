package duke.io;

import duke.command.Commands;
import duke.exception.DukeException;

public class Parser {
    /**
     * Parses the user input for the first word, then calls the appropriate function for the command
     *
     * @param input User input
     * @throws DukeException Any error thrown by Duke when executing the command
     */
    public void parse(String input) throws DukeException {
        String firstWord = input.split(" ")[0];

        for (Commands command : Commands.values()) {
            if (command.isCommand(firstWord)) {
                command.getCommand().parse(input);
                // terminate out of loop once the command is found
                return;
            }
        }

        // handle case where all commands don't match
        throw new DukeException("Unsupported command");
    }
}
