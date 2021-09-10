package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        boolean outOfBound = this.index < 1 || this.index > taskList.size();
        if (outOfBound) {
            throw new DukeException("Index provided is out of bound. Fail to mark task");
        }

        taskList.markDone(this.index - 1);
        ui.sayMarkDoneTask(this.index);
        storage.store(taskList.serialize());
    }
}
