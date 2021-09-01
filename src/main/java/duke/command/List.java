package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UiInterface;

/**
 * Class that handles the List command
 */
public class List extends Command {

    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) {
        ui.print(taskList.stringifyTasksForList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
