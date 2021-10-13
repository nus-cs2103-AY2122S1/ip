package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.Task;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * Represents a delete command where a task is deleted from the TaskList.
 */
public class DeleteCommand extends Command {

    private int index;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.size()) {
            throw new DukeException("Please enter the number of a task that exists in the list of tasks");
        }
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        return ui.printDeletedTask(task);
    }
}
