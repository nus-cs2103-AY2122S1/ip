package duke;

import java.io.IOException;

/**
 * Encapsulates a command by the user to delete a task from Duke's to-do-list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for a delete command.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     * @return A message describing the result of the execution.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Handle user inputs that are out of bounds
        if (index > tasks.getSize() || index <= 0) {
            throw new DukeException("OOPS!!! I'm sorry, but you've entered an invalid index.");
        }

        // Delete task from list of tasks, and persist to text file
        Task t = tasks.delete(index);
        assert !t.equals(null);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }

        // Return a description of the execution result
        return "Noted. I've removed this task: \n"
                + t
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
}
