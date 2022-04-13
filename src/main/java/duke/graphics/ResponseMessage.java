package duke.graphics;

public class ResponseMessage {
    private final String msg;
    private final boolean isExit;

    public ResponseMessage(String msg, boolean isExit) {
        this.msg = msg;
        this.isExit = isExit;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isExit() {
        return isExit;
    }
}
