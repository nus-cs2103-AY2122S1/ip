package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete command where a task is deleted from the TaskList.
 */
public class DeleteCommand extends Command {

    int index;

    /**
     * Creates a delete command where it has the index of the task in the TaskList to be deleted.
     * @param index the index of the task in the TaskList to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns false since a delete command is not an exit command.
     * @return false since it is not a delete command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the delete command, deleting the requested task and printing out a confirmation for the user to see.
     * @param tasks the TaskList where all the tasks are stored.
     * @param ui The ui to print out the user interface and to get input from user.
     * @param storage The storage that interacts with the file to save and load tasks.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        ui.printDeletedTask(task);
    }
}
