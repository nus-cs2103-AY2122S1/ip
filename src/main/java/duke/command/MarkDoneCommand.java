package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a duke.command to mark a duke.task as done.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class MarkDoneCommand extends Command {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private int taskNum;
    /**
     * Class constructor.
     *
     * @param taskNumber the serial number of the duke.task.
     */
    public MarkDoneCommand(String taskNumber) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.Exceptions.EXCEPTIONS);
        }
        assert taskNum >= 1 : "TASK_NUM must be a cardinal value";
        assert !isExit() : "isExit should return false";
    }

    /**
     * Executes the duke.command to mark a duke.task as done.
     *
     * @throws DukeException exception handled by DukeException class
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.markDone(taskNum - 1);
            assert task != null : "task cannot be null";
            return ui.showMarkTaskDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.IndexOutOfBoundsException);
        }
    }
}
