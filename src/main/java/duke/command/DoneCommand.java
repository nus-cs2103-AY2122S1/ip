package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Storage;
import duke.task.Task;

/**
 * Represents a Command that marks a Task as Done.
 */
public class DoneCommand extends Command {
    /**
     * The task number to be marked as done.
     */
    private Integer taskNum;

    /**
     * Constructs a done command with a task number.
     * @param taskNum The task number to be marked as done.
     */
    public DoneCommand(Integer taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the task done command.
     *
     * @param tasks The task list to execute the command on.
     * @param ui The user interface.
     * @param storage The storage for the tasks.
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markTaskDone(taskNum);

        ui.showMessage("Good work! I've marked this task as done:");
        ui.showMessage(task.toString());

        storage.save(tasks.getListData());
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}