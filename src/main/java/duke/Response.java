package duke;

public class Response {
    private boolean isContinue;
    private ResponseMessage message;

    Response(boolean isContinue, ResponseMessage message) {
        this.isContinue = isContinue;
        this.message = message;
    }

    protected boolean isContinue() {
        return isContinue;
    }

    @Override
    public String toString() {
        return message.toString();
    }
}
