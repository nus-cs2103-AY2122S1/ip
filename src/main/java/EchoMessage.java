public class EchoMessage extends DukeMessage{
    private String userString;

    public EchoMessage(String userStr) {
        userString = userStr;
    }

    public void display() {
        System.out.println(userString);
        Duke.conversationState = true;
    }
}
