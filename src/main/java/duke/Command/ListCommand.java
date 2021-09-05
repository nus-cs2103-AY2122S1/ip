package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

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
