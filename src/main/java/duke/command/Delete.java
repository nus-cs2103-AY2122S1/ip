package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.NoListException;
import duke.task.Task;

/**
 * Represents a type of Command that deletes a task from a tasklist.
 */
public class Delete extends Command {
    private int index;

    /**
     * Constructs the Command Delete.
     *
     * @param index The index of the task to be deleted.
     */
    public Delete(int index) {
        this.index = index;
    }

    /**
     * Performs the deletion of the task from the tasklist.
     *
     * @param tasks The tasklist.
     * @param ui The ui.
     * @param storage The storage.
     * @throws NoListException If there is no list to be loaded.
     */
    public String exec(TaskList tasks, Ui ui, Storage storage) throws NoListException {
        Task temp = tasks.get(index - 1);
        tasks.delete(index);
        try {
            storage.save(tasks);
            return "Noted. I've removed this task:\n"
                    + temp.toString() + "\n"

                    + "Now you have " + tasks.size() + " tasks in the list.";
        } catch (NoListException e) {
            throw new NoListException(e);
        }
    }
}
