package aisu.command;

import aisu.AisuException;
import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;
import aisu.task.Task;

/**
 * Command to delete a task from the tasklist.
 *
 * @author Liaw Xin Yan
 */
public class DeleteCommand extends Command {
    private final int parseInt;

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
        this.uiText = ui.formatText(" Noted~ I've removed this task:",
                " - " + deletedTask,
                " Now you have " + tasklist.getListSize() + " task(s) in the list.\n");
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
