package duke;

/**
 * Parses the user's command and makes
 * sense of it.
 */
public class Parser {
    public static String[] parseCommand(String userInput) {
        return userInput.split("\\s", 2);
    }

}
