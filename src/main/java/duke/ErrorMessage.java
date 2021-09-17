package duke;

/**
 * Represents Duke' response when the user's input is exceptional and
 * cannot be normally handled
 */
public class ErrorMessage extends DukeMessage{
    private String errMsg;

    public ErrorMessage(String errMsg) {
        this.errMsg = errMsg;
    }

    public String createMessageString() {
        return errMsg;
    }
}
