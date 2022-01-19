package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/** A class for delete command. */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a delete command.
     *
     * @param index The index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param taskList The task list.
     * @param ui The UI object.
     * @param storage The storage.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        boolean outOfBound = this.index < 1 || this.index > taskList.size();
        if (outOfBound) {
            throw new DukeException("Index provided is out of bound. Fail to delete task");
        }

        Task deletedTask = taskList.getTask(this.index - 1);
        taskList.deleteTask(this.index - 1);
        ui.sayDeleteTask(deletedTask, taskList.size());
        storage.store(taskList.serialize());
    }
}
