/**
 * Encapsulates a greeting message used when Duke starts.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for greeting.
 */
public class DukeGreetingMessage extends DukeOutputMessage {
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
