package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to list all tasks
 */
public class ListCommand extends Command {

    /**
     * List all tasks
     * @param tasks Current TaskList
     * @param ui Ui object of bot
     * @param storage Storage object of bot
     * @return List of all tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        result += ("All tasks:" + "\n");
        result += (tasks.allTasks());
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
