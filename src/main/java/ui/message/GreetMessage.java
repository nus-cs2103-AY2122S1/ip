package ui.message;

/**
 * Encapsulates a greeting message used when Duke starts.
 * It inherits from `ui.message.DukeOutputMessage`.
 * It overrides methods in `ui.message.DukeOutputMessage` to format the message differently for greeting.
 */
public class GreetMessage extends Message {
    /**
     * Constructor to instantiate a `ui.message.DukeGreetingMessage`.
     * Instantiates a parent `ui.message.DukeOutputMessage`.
     *
     * @param message the string to be used in the greeting message
     */
    public GreetMessage(String message) {
        super(message, "٩(｡•́‿•̀｡)۶");
    }
}
