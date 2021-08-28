package duke.command;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

import java.io.IOException;

/** A class for mark command */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a mark command.
     *
     * @param index The index of task to be marked.
     */
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
