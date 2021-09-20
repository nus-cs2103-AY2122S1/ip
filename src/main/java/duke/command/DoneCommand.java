package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.errors.FileException;

/**
 * Type of Command that marks tasks as done in the task list.
 */
public class DoneCommand extends Command {
    private int taskToMarkAsDone;

    /**
     * Constructor.
     *
     * @param markAsDone position of task to mark as done in the task list.
     */
    public DoneCommand(int markAsDone) {
        this.taskToMarkAsDone = markAsDone;
    }

    /**
     * Executes a series of operations to mark the task as done.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @param archive
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws FileException {
        taskList.getTasks().get(taskToMarkAsDone - 1).markAsDone();
        storage.markAsDone(taskList, taskToMarkAsDone);
        return ui.done(taskList, taskToMarkAsDone);
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
