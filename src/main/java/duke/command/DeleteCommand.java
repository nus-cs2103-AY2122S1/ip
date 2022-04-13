package duke.command;

import duke.util.Keyword;
import duke.util.Message;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for delete.
 *
 * @author marcuspeh
 * @version A-Assertions
 * @since 6 Sep 2021
 */
public class DeleteCommand implements Command {
    /** Stores the message entered by the . */
    private String message;

    /** Constructor for duke.command.DeleteCommand.
     *
     * @param message Stores the message entered by the user.
     */
    public DeleteCommand(String message) {
        this.message = message;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui To interact with the user.
     * @return message to be used by either the graphic UI or command line UI.
     */
    @Override
    public Message execute(TaskList taskList, Ui ui) {
        assert taskList != null : " Tasklist is required by command.";
        assert ui != null : " Ui is required by command.";

        try {
            assert message.length() > Keyword.DELETE.length() : "Delete is in the following format 'delete <number>'";
            return taskList.deleteTask(Integer.parseInt(message.substring(Keyword.DELETE.length() + 1)));
        } catch (NumberFormatException e) {
            return ui.formatDeleteErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            return ui.formatDeleteIndexErrorMessage();
        }
    }
}
