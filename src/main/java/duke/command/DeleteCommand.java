package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * DeleteCommand class handles the commands that deletes a particular task in the tasklist.
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    /**
     * Constructs the DeleteCommand object.
     *
     * @param taskNumber Task number of the task to be edited.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task with index taskNumber from the tasklist
     * and returns the response message.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If taskNumber is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task deletedTask = taskList.removeTask(taskNumber - 1);
        storage.saveList(taskList.convertToFileFormat());
        return "Alright! I've deleted this task:\n" + deletedTask + taskList.getListStatus();
    }


}

