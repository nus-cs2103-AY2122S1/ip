package duke;

/**
 * This class parses the user input into a command understood by Duke.
 */
public class Parser {

    /**
     * This method returns the first word of raw user input.
     *
     * @param input Raw user input.
     * @return The first word which is the command for Duke to understand.
     */
    public String parse(String input) {
        return input.split(" ")[0];
    }
}