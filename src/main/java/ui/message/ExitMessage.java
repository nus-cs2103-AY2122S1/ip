package ui.message;

/**
 * Encapsulates an exit message used when Duke exits.
 * It inherits from `ui.message.DukeOutputMessage`.
 * It overrides methods in `ui.message.DukeOutputMessage` to format the message differently for exiting.
 */
public class ExitMessage extends Message {
    /**
     * Constructor to instantiate a `ui.message.DukeExitMessage`.
     * Instantiates a parent `ui.message.DukeOutputMessage`.
     *
     * @param message the string to be used in the exit message
     */
    public ExitMessage(String message) {
        super(message);
    }

    /**
     * Gets the message with a waving face added to it
     *
     * @return the string message with a waving face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + " ヾ(=´･∀･｀=)";
    }
}
