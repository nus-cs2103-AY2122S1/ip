package jarvis.message;

public class OutputMessage {
    private String message;

    public OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormattedMessage() {
        return String.format("\n%s\n", this.getMessage());
    }
}

