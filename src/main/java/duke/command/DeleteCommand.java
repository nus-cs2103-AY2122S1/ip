package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command to delete a Task from the TaskList.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * The constructor for a DeleteCommand object.
     *
     * @param index The given index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a Task.
     *
     * @param tasks The given TaskList to be updated.
     * @param ui The given Ui to print messages to the user.
     * @param storage The given Storage to save changes to.
     * @throws DukeException When the given index does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getSize()) {
            throw new DukeException("This entry does not exist!");
        }

        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printTemplate(" Noted. I've removed this task:\n" + "  " + deletedTask
                + System.lineSeparator() + " Now you have " + tasks.getSize() + " tasks in the list.");
        storage.update(tasks);
    }
}
