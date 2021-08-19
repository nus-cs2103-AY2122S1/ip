public class MessageFactory {
    public static DukeMessage createMessage(String userStr) {
        if(userStr == null) {
            return new GreetMessage();
        } else if (userStr.equals("bye")) {
            return new ExitMessage();
        } else if (userStr.equals("list")) {
            return new GetTasks();
        } else {
            return new AddTask(userStr);
        }
    }
}
