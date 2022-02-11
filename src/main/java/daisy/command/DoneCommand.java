package daisy.command;

import daisy.DaisyException;
import daisy.task.Storage;
import daisy.task.Task;
import daisy.task.TaskList;

/**
 * DoneCommand class handles the commands that marks a
 * particular task in the task list as done.
 */
public class DoneCommand extends Command {

    static final String DONE_HEADER = "Good work! Task is now marked as done:\n";
    private int taskNumber;
    /**
     * Constructs the DoneCommand object.
     *
     * @param taskNumber Task number of the task to be edited.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task with index taskNumber in the task list as done
     * and returns the response message.
     *
     * @param taskList The TaskList of Daisy.
     * @param storage The Storage of Daisy.
     * @return Response string.
     * @throws DaisyException  If taskNumber is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DaisyException {
        Task completedTask = taskList.markTaskAsDone(taskNumber - 1);
        storage.saveList(taskList.convertToFileFormat());
        return DONE_HEADER + completedTask;
    }


}
