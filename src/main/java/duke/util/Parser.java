package duke.util;

public class Parser {
    /**
     * Parses input to equivalent DukeCommands enum
     *
     * @param input command to be parsed
     * @return an equivalent DukeCommands enum
     */
    public static DukeCommands parseCommand(String input) {
        return DukeCommands.valueOf(input.toUpperCase());
    }

}
