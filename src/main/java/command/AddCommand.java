package command;

import storage.Storage;

import task.Task;
import task.TaskList;

import ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(this.task);
        ui.sayAddTask(this.task, taskList.size());
        storage.store(taskList.serialize());
    }
}
