package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.displayList();
    }

    public boolean isExit() {
        return false;
    }
}
