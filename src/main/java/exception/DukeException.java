package exception;

import ui.message.Message;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public Message getOutputMessage() {
        return new Message(this.getMessage());
    }
}
