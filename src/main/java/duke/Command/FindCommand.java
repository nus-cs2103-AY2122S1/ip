package duke.Command;

import java.util.ArrayList;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find tasks
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs find command
     * @param keyword keyword used to search tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks that matches keyword
     * @param tasks Current TaskList
     * @param ui Ui object of bot
     * @param storage Storage object of bot
     * @return List of tasks found
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        list.removeIf(task -> !task.getDescription().contains(this.keyword));
        String result = "";
        if (list.size() == 0) {
            result += ("No matching tasks found.");
        } else {
            result += (list.size() + " matching task(s):" + "\n");
            result += (new TaskList(list).allTasks());
        }
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
