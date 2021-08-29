package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddCommand extends Command{
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.addTask(task);
    }
}