package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.Task;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * Represents a done command that has a index of the task in the TaskList that is to be marked as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Creates a done command with the index of the task in the TaskList to be marked as done.
     * @param index the index of the task in the TaskList to be marked as done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Returns false since a done command is not an exit command.
     * @return a boolean that is false as it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command where the task at the index of the ArrayList is marked as done.
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
        tasks.markTask(index);
        storage.save(tasks.getTasks());
        return ui.markTask(task);
    }
}
