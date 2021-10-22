package duke;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import duke.command.DukeCommand;
import duke.command.DukeCommandWithArgs;

/**
 * Parses the raw commands (in string form) to objects representing them.
 */
public class Parser {
    /**
     * Parses the command given as a raw text input, and returns a {@link DukeCommandWithArgs} representing it
     *
     * @param command the command as a string
     * @return a {@link DukeCommandWithArgs} representing the full command
     */
    public DukeCommandWithArgs parse(String command) {
        // Get the longest duke command that matches to command
        Optional<DukeCommand> dukeCommand = DukeCommand.getClosestMatch(command);
        if (dukeCommand.isPresent()) {
            DukeCommand actualCommand = dukeCommand.get();
            String arguments = command.substring(actualCommand.getName().length());
            String[] tokens = arguments.split("/");
            String positionalArg = tokens[0].trim();
            Map<String, String> namedArgs = new HashMap<>();
            for (int i = 1; i < tokens.length; i++) {
                String[] namedArg = tokens[i].trim().split(" ", 2);
                namedArgs.put(namedArg[0].trim(), namedArg[1].trim());
            }
            return new DukeCommandWithArgs(actualCommand, positionalArg, namedArgs);
        } else {
            return null;
        }
    }
}
