package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.NoListException;

/**
 * Represents a type of Command that marks a task in a tasklist as done.
 */
public class Done extends Command {
    private int index;

    /**
     * Constructs the Command Done.
     *
     * @param index The index of the task to be marked as done.
     */
    public Done(int index) {
        this.index = index;
    }

    /**
     * Performs the marking of the task in the tasklist.
     *
     * @param tasks The tasklist.
     * @param ui The ui.
     * @param storage The storage.
     * @throws NoListException If there is no list to be loaded.
     */
    public String exec(TaskList tasks, Ui ui, Storage storage) throws NoListException {
        try {
            tasks.get(this.index - 1).markAsDone();
            storage.save(tasks);
            return "Done.";
        } catch (NoListException e) {
            throw e;
        }
    }
}
