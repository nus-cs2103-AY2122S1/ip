package duke.command;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task deletedTask = taskList.getTask(this.index - 1);
        taskList.deleteTask(this.index - 1);
        ui.sayDeleteTask(deletedTask, taskList.size());
        storage.store(taskList.serialize());
    }
}
