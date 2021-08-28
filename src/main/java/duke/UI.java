package duke;

/**
 * Encapsulates a class that contains methods for interacting with the user
 */
public class UI {

    /**
     * Returns a single String that is multiple Strings from a string array appended with new lines
     *
     * @param messages The provided String Array
     * @return The new String that is formatted
     */
    public String formatMessage(String[] messages) {
        StringBuilder res = new StringBuilder();
        for(String s : messages) {
            res.append(s).append("\n");
        }
        return res.toString();
    }

    /**
     * Returns a String as a welcome message
     *
     * @return the String message
     */
    public String welcomeMessage() {
        return "Hello! I'm duke.Duke\n" + "     What can I do for you?";
    }

    /**
     * Returns a String as a goodbye message
     *
     * @return the String message
     */
    public String goodByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a String as a response to an unrecognised command
     *
     * @return the String response
     */
    public String unrecognisedCommand() {
        return "That is not a recognised command";
    }

}
