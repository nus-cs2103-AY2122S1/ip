package entity.message;

/**
 * Encapsulates an exit message used when Duke exits.
 * It inherits from `entity.message.DukeOutputMessage`.
 * It overrides methods in `entity.message.DukeOutputMessage` to format the message differently for exiting.
 */
public class ExitMessage extends Message {
    /**
     * Constructor to instantiate a `entity.message.DukeExitMessage`.
     * Instantiates a parent `entity.message.DukeOutputMessage`.
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
