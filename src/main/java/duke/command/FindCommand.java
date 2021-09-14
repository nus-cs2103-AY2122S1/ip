package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
/**
 * Represents the command to find the task.
 */
public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        output.append(ui.returnMessage("Here are the matching tasks in your list:"));
        output.append("\n");
        int index = 1;
        for (Task task : taskList.getAllTasks()) {
            if (task.isMatchingTask(this.keyword)) {
                output.append(ui.returnMessage(String.format("%d.%s%n", index++, task)));
            }
        }
        return output.toString();
    }
}
