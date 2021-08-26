package duke.command;

import duke.util.Ui;
import duke.util.Storage;

import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Goodbye. Have a nice day!");
        return tasks;
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
