public class MessageFactory {
    public DukeMessage createMessage(String userStr) {
        if(userStr == null) {
            return new GreetMessage();
        } else if (userStr.equals("bye")) {
            return new ExitMessage();
        } else {
            return new EchoMessage(userStr);
        }
    }
}
