/**
 * Encapsulates a greeting message used when Duke starts.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for greeting.
 */
public class DukeGreetingMessage extends DukeOutputMessage {
    /**
     * Constructor to instantiate a `DukeGreetingMessage`.
     * Instantiates a parent `DukeOutputMessage`.
     *
     * @param message the string to be used in the greeting message
     */
    public DukeGreetingMessage(String message) {
        super(message);
    }

    /**
     * Gets the message with a cheering face added to it
     *
     * @return the string message with a cheering face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + " ٩(｡•́‿•̀｡)۶";
    }
}
