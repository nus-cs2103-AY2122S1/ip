package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Type of Command that marks tasks as done in the task list.
 */
public class DoneCommand extends Command {
    private int markAsDone;

    /**
     * Constructor.
     *
     * @param markAsDone position of task to mark as done in the task list.
     */
    public DoneCommand(int markAsDone) {
        this.markAsDone = markAsDone;
    }

    /**
     * Executes a series of operations to mark the task as done.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTasks().get(markAsDone - 1).markAsDone();
        storage.markAsDone(taskList, markAsDone);
        ui.done(taskList, markAsDone);
    }

    /**
     * Not an Exit Command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
