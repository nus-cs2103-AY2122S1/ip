package duke.command;

import duke.util.Keyword;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for delete.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
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
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.deleteTask(Integer.parseInt(message.substring(Keyword.DELETE.length() + 1)));
        } catch (NumberFormatException e) {
            ui.deleteErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.deleteIndexErrorMessage();
        }
    }
}
