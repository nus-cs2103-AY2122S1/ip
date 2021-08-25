package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to display tasks in list.
 */
public class ListCommand implements Command {

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.displayList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
