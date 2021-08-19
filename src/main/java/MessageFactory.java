public class MessageFactory {
    public static DukeMessage createMessage(String userStr) {
        if(userStr == null) {
            return new GreetMessage();
        } else if (userStr.equals("bye")) {
            return new ExitMessage();
        } else if (userStr.equals("list")) {
            return new GetTasks();
        } else if (userStr.substring(0, 4).equals("done")) {
            int len = userStr.length();
            int taskIndex = userStr.charAt(len - 1) - 49;
            return new CompleteTask(taskIndex);
        } else {
            return new AddTask(userStr);
        }
    }
}
