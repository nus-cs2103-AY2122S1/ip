package exception;

import message.Message;

/**
 * Encapsulates an exception that contains a method to get a 'Message`.
 * Duke exceptions contain messages that should be shown to the user.
 */
public class DukeException extends Exception {
    protected DukeException(String message) {
        super(message);
    }

    /**
     * Gets an output message containing information about why the error occurred.
     * And/or how the user can correct the error.
     *
     * @return `Message`.
     */
    public Message getOutputMessage() {
        return new Message(this.getMessage());
    }
}
