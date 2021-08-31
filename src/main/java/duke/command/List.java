package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that handles the List command
 */
public class List extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print(taskList.stringifyTasksForList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
