package aisu.command;

import aisu.AisuException;
import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;
import aisu.task.Task;

/**
 * Command to mark a task as done.
 *
 * @author Liaw Xin Yan
 */
public class MarkDoneCommand extends Command {
    private final int parseInt;

    public MarkDoneCommand(int parseInt) {
        this.parseInt = parseInt;
    }

    /**
     * Marks task as complete and updates tasklist.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage  Storage used in Aisu.
     * @param ui       User interface used in Aisu.
     * @throws AisuException If command fails to be executed.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        Task completedTask = tasklist.markDone(this.parseInt);
        storage.save(tasklist);
        this.uiText = ui.formatText(" Nice! I've marked this task as completed:", completedTask.toString());
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
