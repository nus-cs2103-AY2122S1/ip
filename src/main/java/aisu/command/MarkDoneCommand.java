package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.task.Task;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to mark a task as done.
 *
 * @author Liaw Xin Yan
 */
public class MarkDoneCommand extends Command {
    private final int parseInt;

    /**
     * Constructor to initialise the Mark Done command.
     * @param parseInt The index of the task to be marked as done.
     */
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
        this.uiText = Ui.formatText("Nice! I've marked this task as completed:", completedTask.toString());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return false;
    }
}
