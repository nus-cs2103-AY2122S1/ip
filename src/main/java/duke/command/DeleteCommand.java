package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.delete(this.taskToDelete);
        ui.delete(taskList, this.taskToDelete);
        taskList.deleteTask(this.taskToDelete);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
