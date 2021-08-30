package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * ListCommand prints out current task list.
 *
 * @author Chng Zi Hao
 */
public class ListCommand extends Command {

    /**
     * Deletes task from the task list.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message = taskList.printTaskList();
        ui.formatPrint(message);
        return message;
    }
}
