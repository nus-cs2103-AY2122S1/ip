package duke;

/**
 *  A class that encapsulates the interactions with the user.
 */
public class Ui {
    /**
     * Shows the welcome message when first executing Duke.
     */
    public static String showWelcomeMessage() {
        String logo = "DUKE!!";
        StringBuilder result = new StringBuilder("Hello from \nWhat can I do for you\n");
        result.insert(11, logo);
        return result.toString();
    }

    /**
     * Shows the error message when encountering a DukeException.
     */
    public static String showErrorMessage(DukeException e) {
        String border = "******************************";
        StringBuilder result = new StringBuilder(border);
        result.append(border).append("\n\n")
                .append(e.toString()).append("\n\n")
                .append(border).append(border).append("\n");
        return result.toString();
    }

    /**
     * Shows the BreakLine that is shown at the start and end of each Duke's reply.
     */
    public static String showBreakLine() {
        String breakLine = "------------------------------";
        StringBuilder result = new StringBuilder(breakLine);
        result.append(breakLine).append("\n");
        return result.toString();
    }

    /**
     * Shows the formatted reply from Duke after receiving an input from the user.
     *
     * @param reply The String representation of the reply provided from Duke
     */
    public static String showReply(String reply) {
        StringBuilder result = new StringBuilder(reply);
        result.insert(0, showBreakLine()).append(showBreakLine());
        return result.toString();
    }
}
