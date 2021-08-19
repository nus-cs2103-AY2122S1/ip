public class MessageFactory {
    public static DukeMessage createMessage(String userStr) {
        try {
            if (userStr == null) {
                return new GreetMessage();
            } else if (userStr.equals("bye")) {
                return new ExitMessage();
            } else if (userStr.equals("list")) {
                return new GetTasks();
            } else if (userStr.contains("done")) {
                int len = userStr.length();
                int taskIndex = userStr.charAt(len - 1) - 49;
                return new CompleteTask(taskIndex);
            } else {
                try {
                    return new AddTask(userStr);
                } catch (IllegalFormatException | EmptyDescriptionException | InvalidCommandException exception) {
                    return new ErrorMessage(exception.getMessage());
                }
            }
        } catch (Exception e) {
            return new ErrorMessage("kya likhra h bhai??");
        }
    }
}
