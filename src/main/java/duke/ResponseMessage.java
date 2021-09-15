package duke;

public class ResponseMessage {
    private static final String NULL_STRING = "";
    private String message;

    ResponseMessage() {
        this.message = NULL_STRING;
    }

    ResponseMessage(String message) {
        this.message = message;
    }

    protected void appendMessage(String s) {
        if (message.equals(NULL_STRING)) {
            this.message += s;
            return;
        }
        this.message += "\n" + s;
    }

    @Override
    public String toString() {
        return message;
    }
}
