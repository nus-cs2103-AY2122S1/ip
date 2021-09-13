package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

/** The Command class responsible for marking tasks as done. */
public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Initializes the task number of the task to be marked as done.
     *
     * @param taskNumber The task number of the task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException {
        return tasks.markTaskAsDone(taskNumber, storage, responseLogic);
    }
}
