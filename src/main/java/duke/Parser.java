package duke;

/**
 * Reads the command input and processes it to decipher what actions Duke should carry out.
 */
public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    /**
     * Reads the command and processes the first word to determine what the command type is.
     *
     * @param command Command that is passed into the parser.
     * @return Type of command in String format.
     */
    public static String process(String command) {
        String[] words = command.split(" ");
        String init = words[0];
        return init;
    }

}
