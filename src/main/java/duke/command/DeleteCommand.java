package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Represents a Command to delete a Task from the TaskList.
 */
public class DeleteCommand extends Command {

    private int index;
    private String reply;

    /**
     * The constructor for a DeleteCommand object.
     *
     * @param index The given index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Command to delete a Task.
     *
     * @param tasks The given TaskList to be updated.
     * @param storage The given Storage to save changes to.
     * @return The response to the user.
     * @throws DukeException When the given index does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index > tasks.getSize()) {
            throw new DukeException("This entry does not exist!");
        }

        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        reply = "Noted. I've removed this task:\n" + "  " + deletedTask
                + System.lineSeparator() + "Now you have " + tasks.getSize() + " tasks in the list.";
        storage.update(tasks);
        return reply;
    }
}
