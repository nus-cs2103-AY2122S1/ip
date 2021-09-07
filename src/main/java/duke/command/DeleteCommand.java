package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;


/**
 * Represents a duke.command to delete a duke.task.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class DeleteCommand extends Command {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final int TASK_NUM;

    /**
     * Class constructor.
     *
     * @param taskNumber the serial number of the duke.task.
     */
    public DeleteCommand(int taskNumber) {
        this.TASK_NUM = taskNumber;
    }

    /**
     * Executes the duke.command to delete a duke.task.
     *
     * @param tasks   lists of tasks
     * @param ui      the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.delete(TASK_NUM - 1);
            int numTasksRemaining = tasks.getNumTasks();
            ui.showTaskDeleted(deletedTask, numTasksRemaining);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e);
        }
    }
}
