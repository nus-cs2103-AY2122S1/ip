package duke;

/**
 * Parser class to make sense of user's input
 */
public class Parser {
    /**
     * Converts the letters to lowercase to accept commands with uppercase letters
     * @param command takes in a String representing user's command
     * @return a String with only lower case letters representing user's command
     */
    public static String parseCommand(String command){
        return command.toLowerCase();
    }

}
