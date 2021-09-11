package duke;

import duke.AddTaskMessage;

/**
 * The factory class responsible for instantiating the right sub-type of DukeMessage.
 */
public class MessageFactory {
    public static DukeMessage createMessage(String userStr) {
        try {
            if (userStr.equals("list")) {
                return new GetTasksMessage();
            } else if (userStr.length() >= 4 && userStr.substring(0,4).equals("done")) {
                int len = userStr.length();
                int taskIndex = userStr.charAt(len - 1) - 49;
                if (taskIndex >= TaskList.getTaskList().getTasks().size() || taskIndex < 0)
                    throw new ArrayIndexOutOfBoundsException();
                return new CompleteTaskMessage(taskIndex);
            } else if (userStr.length() >= 6 && userStr.substring(0,6).equals("delete")) {
                int len = userStr.length();
                int taskIndex = userStr.charAt(len - 1) - 49;
                if (taskIndex >= TaskList.getTaskList().getTasks().size())
                    throw new ArrayIndexOutOfBoundsException();
                return new DeleteTaskMessage(taskIndex);
            } else if (userStr.length() >= 4 && userStr.substring(0,4).equals("find")) {
                int len = userStr.length();
                String searchStr = userStr.substring(5);
                return new FindMessage(searchStr);
            } else {
                return new AddTaskMessage(userStr);
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
