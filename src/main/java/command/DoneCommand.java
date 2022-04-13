package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

public class DoneCommand extends Command {
    /** The index of the task to be marked as done. **/
    private final int taskIndex;

    /**
     * A public constructor to initialized the given DoneCommand.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * A method to execute the DoneCommand. When the method is executed,
     * the corresponding task will be marked as done, and message will
     * be send to the given Ui.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException Exception thrown when execute the DoneCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task doneTask = taskList.markAsDone(this.taskIndex);
        return ui.generateDukeResponse(
            "Nice! I've marked this task as done:",
            doneTask.toString());
    }
}
