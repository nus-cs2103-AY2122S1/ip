package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * A class which encapsulates the command of
 * deleting a task.
 */
public class DeleteCommand extends Command {


    /** The string containing the command */
    private String command;

    /** Index of the task to be deleted.*/
    private int index;

    /**
     * A public constructor to initialise the command
     * and index to the given one.
     *
     * @param command The command inputted by the user.
     */
    public DeleteCommand(String command, int index) {
        super(command);
        this.command = command;
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks stored so far.
     * @param ui The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     * @return The string indicating the delete command is executed.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String toDelete = ui.removeTask(tasks.get(index - 1));
        tasks.delete(index);
        try {
            storage.editFileAll(tasks);
        } catch(DukeException e) {
            return e.getMessage();
        }
        return toDelete + System.lineSeparator() + ui.numberOfTasks(tasks);
    }
}
