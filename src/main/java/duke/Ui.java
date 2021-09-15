package duke;

/**
 * Encapsulates a class that contains methods for interacting with the user.
 */
public class Ui {

    /**
     * Returns a single String that is multiple Strings from a string array appended with new lines.
     *
     * @param messages The provided String Array.
     * @return The new String that is formatted.
     */
    public String formatMessage(String[] messages) {
        assert messages.length > 0 : "Messages array should not be empty";
        StringBuilder res = new StringBuilder();
        for (String s : messages) {
            res.append(s).append("\n");
        }
        return res.toString();
    }

    /**
     * Returns a String as a response to an unrecognised command.
     *
     * @return the String response.
     */
    public String getUnrecognisedCommandMessage() {
        return "That is not a recognised command";
    }

}
