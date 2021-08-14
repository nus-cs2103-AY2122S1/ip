/**
 * Encapsulates a added message used to inform a user that a message has been added to a list.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for adding.
 */
public class DukeAddedMessage extends DukeOutputMessage {
    private static String ADDED_PREFIX = "added: ";

    /**
     * Constructor to instantiate a `DukeAddedMessage`.
     * Instantiates a parent `DukeOutputMessage`.
     *
     * @param message the string to be used in the added message
     */
    public DukeAddedMessage(String message) {
        super(message);
    }

    /**
     * Gets the message with an `added: ` prefix
     *
     * @return the string message with an `added: ` prefix
     */
    @Override
    public String getMessage() {
        return ADDED_PREFIX + super.getMessage();
    }

    /**
     * Gets the message with a thumbs up face added to it
     *
     * @return the string message with a thumbs up face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + " (＾＾)b";
    }
}
