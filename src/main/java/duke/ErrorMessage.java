package duke;

public class ErrorMessage extends DukeMessage{
    private String errMsg;

    public ErrorMessage(String errMsg) {
        this.errMsg = errMsg;
    }

    public String display() {
        return errMsg;
    }
}
