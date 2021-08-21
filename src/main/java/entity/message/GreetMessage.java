package entity.message;

/**
 * Encapsulates a greeting message used when Duke starts.
 * It inherits from `entity.message.DukeOutputMessage`.
 * It overrides methods in `entity.message.DukeOutputMessage` to format the message differently for greeting.
 */
public class GreetMessage extends Message {
    /**
     * Constructor to instantiate a `entity.message.DukeGreetingMessage`.
     * Instantiates a parent `entity.message.DukeOutputMessage`.
     *
     * @param message the string to be used in the greeting message
     */
    public GreetMessage(String message) {
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
