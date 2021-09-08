package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * to the given one.
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
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        ui.removeTask(tasks.get(index - 1));
        tasks.delete(index);
        storage.editFileAll(tasks);
        return ui.numberOfTasks(tasks);
    }
}
