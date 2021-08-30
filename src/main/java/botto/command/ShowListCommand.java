package botto.command;

import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

/**
 * Command for printing the user tasks
 */
public class ShowListCommand implements Command {

    /**
     * print the user tasks
     *
     * @param taskList the task list involved
     * @param ui the ui of the Botto bot
     * @param storage storage of the Botto bot
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList.getTasks(), "Here are the tasks in your list:");
    }
}
