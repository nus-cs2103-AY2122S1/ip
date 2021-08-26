package duke;

public class Parser {

    public Parser() { }

    /**
     * Gets the command entered by the user.
     *
     * @param userInput The string keyed in by the user
     * @return The first word keyed in by the user
     */
    public String getUserCommand(String userInput) {
        if (userInput.indexOf(' ') <= 0) {
            return userInput;
        }
        return userInput.substring(0, userInput.indexOf(' '));
    }

    /**
     * Gets the argument entered by the user.
     *
     * @param userInput The string keyed in by the user
     * @return The arguments entered by the user (all words after the first word)
     */
    public String getUserArgument(String userInput) {
        if (userInput.indexOf(' ') <= 0) {
            return "";
        }
        try {
            return userInput.substring(userInput.indexOf(' ') + 1).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

}
