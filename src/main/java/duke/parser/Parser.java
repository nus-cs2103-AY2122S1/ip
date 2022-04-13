package duke.parser;

/**
 * Contains methods which parse the user's input.
 */
public class Parser {

    /**
     * Parses the user's input.
     *
     * @param command Command from the user.
     * @return An array with the first element being the instruction.
     */
    public static String[] parse(String command) {
        return command.split(" ", 2);
    }
}
