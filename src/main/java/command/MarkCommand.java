package command;

import storage.Storage;

import task.TaskList;

import ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markDone(this.index - 1);
        ui.sayMarkDoneTask(this.index);
        storage.store(taskList.serialize());
    }
}
