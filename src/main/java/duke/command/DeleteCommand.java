package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.storage.Storage;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = TaskList.get(this.index);
        taskList.deleteTask(this.index);
        ui.deleteTask(task);
    }
}
