package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a duke.command to mark a duke.task as done.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class MarkDoneCommand extends Command {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final int TASK_NUM;

    /**
     * Class constructor.
     *
     * @param taskNumber the serial number of the duke.task.
     */
    public MarkDoneCommand(int taskNumber) {
        this.TASK_NUM = taskNumber;
    }

    /**
     * Executes the duke.command to mark a duke.task as done.
     *
     * @throws DukeException exception handled by DukeException class
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.markDone(TASK_NUM - 1);
            ui.showMarkTaskDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e);
        }
    }
}
