package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        output += (ui.returnMessage("Here are the matching tasks in your list:"));
        output += "\n";
        int index = 1;
        for (Task task : taskList.getAllTasks()) {
            if (task.isMatchingTask(this.keyword)) {
                output += ui.returnMessage(String.format("%d.%s", index++, task));
            }
        }
        return output;
    }
}
