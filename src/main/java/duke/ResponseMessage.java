package duke;

public class ResponseMessage {
    private String message;

    ResponseMessage() {
        this.message = "";
    }

    ResponseMessage(String message) {
        this.message = message + "\n";
    }

    public void appendMessage(String s) {
        this.message += s + "\n";
    }

    public void appendNewLine() {
        this.message += Ui.printLine();
    }

    @Override
    public String toString() {
        return message;
    }
}
