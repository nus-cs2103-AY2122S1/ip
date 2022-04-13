package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

/** The Command class responsible for deleting tasks. */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Initializes the task number of the task to be deleted.
     *
     * @param taskNumber The task number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException {
        return tasks.deleteTask(taskNumber, storage, responseLogic);
    }
}
