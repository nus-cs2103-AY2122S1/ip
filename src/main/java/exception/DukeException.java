package exception;

import entity.message.Message;

public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public Message getOutputMessage() {
        return new Message(this.getMessage());
    }
}
