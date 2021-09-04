package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.task.Task;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to delete a task from the tasklist.
 *
 * @author Liaw Xin Yan
 */
public class DeleteCommand extends Command {
    private final int parseInt;

    /**
     * Constructor to initialise the Delete command.
     * @param parseInt The index of the task to be deleted.
     */
    public DeleteCommand(int parseInt) {
        this.parseInt = parseInt;
    }

    /**
     * Deletes task from tasklist and updates tasklist.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage  Storage used in Aisu.
     * @param ui       User interface used in Aisu.
     * @throws AisuException If command fails to be executed.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        Task deletedTask = tasklist.deleteTask(this.parseInt);
        storage.save(tasklist);
        this.uiText = Ui.formatText(" Noted~ I've removed this task:",
                " - " + deletedTask,
                " Now you have " + tasklist.getListSize() + " task(s) in the list.\n");
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return false;
    }
}
