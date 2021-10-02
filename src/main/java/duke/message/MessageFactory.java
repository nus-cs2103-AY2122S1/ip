package duke.message;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.IllegalFormatException;
import duke.task.TaskList;

/**
 * The factory class responsible for instantiating the right sub-type of DukeMessage.
 */
public class MessageFactory {

    /**
     * Instantiates the right subtype of DukeMessage.
     *
     * @param userStr The string input from the user
     * @return DukeMessage A DukeMessage object which handles user's
     *                      command
     */
    public static DukeMessage createMessage(String userStr) {
        try {
            if (userStr.equals("list")) {
                return new GetAllTasksMessage();
            } else if (userStr.length() >= 4 && userStr.substring(0,4).equals("done")) {
                int len = userStr.length();
                int taskIndex = userStr.charAt(len - 1) - 48;
                return new CompleteTaskMessage(taskIndex);
            } else if (userStr.length() >= 6 && userStr.substring(0,6).equals("delete")) {
                int len = userStr.length();
                int taskIndex = userStr.charAt(len - 1) - 48;
                return new DeleteTaskMessage(taskIndex);
            } else if (userStr.length() >= 4 && userStr.substring(0,4).equals("find")) {
                String searchStr = userStr.substring(5);
                return new FindTasksMessage(searchStr);
            } else {
                return new AddTaskMessage(userStr);
            }
        } catch (IllegalFormatException | EmptyDescriptionException | InvalidCommandException exception) {
            return new ErrorMessage(exception.getMessage());
        } catch (Exception e) {
            return new ErrorMessage("kya likhra h bhai??(message not " +
                    "understood" +
                    ")");
        }
    }
}
