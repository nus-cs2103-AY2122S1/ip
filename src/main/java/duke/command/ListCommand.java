package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to display tasks in list.
 */
public class ListCommand implements Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.displayList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
