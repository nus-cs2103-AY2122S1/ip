package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand(){};

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        ui.listTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
