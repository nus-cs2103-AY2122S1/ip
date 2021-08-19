public class MessageFactory {
    public static DukeMessage createMessage(String userStr) {
        try {
            if (userStr == null) {
                return new GreetMessage();
            } else if (userStr.equals("bye")) {
                return new ExitMessage();
            } else if (userStr.equals("list")) {
                return new GetTasks();
            } else if (userStr.length() >= 4 && userStr.substring(0,4).equals("done")) {
                int len = userStr.length();
                int taskIndex = userStr.charAt(len - 1) - 49;
                if (taskIndex >= TaskList.getTaskList().getTasks().size())
                    throw new ArrayIndexOutOfBoundsException();
                return new CompleteTask(taskIndex);
            } else if (userStr.length() >= 6 && userStr.substring(0,6).equals("delete")) {
                int len = userStr.length();
                int taskIndex = userStr.charAt(len - 1) - 49;
                if (taskIndex >= TaskList.getTaskList().getTasks().size())
                    throw new ArrayIndexOutOfBoundsException();
                return new DeleteTaskMessage(taskIndex);
            } else {
                return new AddTask(userStr);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorMessage("jo task ko select kerra h uska index padha tha??");
        } catch (IllegalFormatException | EmptyDescriptionException | InvalidCommandException exception) {
            return new ErrorMessage(exception.getMessage());
        } catch (Exception e) {
            //System.out.println(e.fillInStackTrace());
            return new ErrorMessage("kya likhra h bhai??");
        }
    }
}
