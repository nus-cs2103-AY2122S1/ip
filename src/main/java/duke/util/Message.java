package duke.util;

/**
 * Class to handle the message from duke.
 *
 * @author marcuspeh
 * @version A-Assertions
 * @since 6 Sep 2021
 */
public class Message {
    /** Message to be printed. */
    private String[] message;

    /**
     * Constructor for Message.
     *
     * @param s Message returned by the command.
     */
    Message(String... s) {
        assert s.length > 0 : "Message cannot be blank.";
        this.message = s;
    }

    /**
     * Prints out the message from the command.
     */
    public void printMessage() {
        Ui.printMessage(message);
    }

    @Override
    public String toString() {
        return String.join("\n", message);
    }
}
