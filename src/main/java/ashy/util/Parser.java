package ashy.util;

public class Parser {
    /**
     * Parses input to equivalent DukeCommands enum
     *
     * @param input command to be parsed
     * @return an equivalent DukeCommands enum
     */
    public static AshyCommands parseCommand(String input) {
        return AshyCommands.valueOf(input.toUpperCase());
    }

}
