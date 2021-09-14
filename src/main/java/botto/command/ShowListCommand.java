package botto.command;

import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

/**
 * Command for printing the user tasks.
 */
public class ShowListCommand implements Command {

    /**
     * Print the user tasks.
     *
     * @param taskList the task list involved.
     * @param dialog the ui of the Botto bot.
     * @param storage storage of the Botto bot.
     */
    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) {
        dialog.showTasks(taskList.getTasks(), "Here are the tasks in your list:");
    }
}
