public class ErrorMessage extends DukeMessage{
    private String errMsg;

    public ErrorMessage(String errMsg) {
        this.errMsg = errMsg;
    }

    public void display() {
        System.out.println(errMsg);
        Duke.conversationState = true;
    }
}
