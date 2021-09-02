package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * DoneCommand class handles the commands that marks a
 * particular task in the tasklist as done.
 */
public class DoneCommand extends Command {

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
     * Marks the task with index taskNumber in the tasklist as done
     * and returns the response message.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If taskNumber is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task completedTask = taskList.markTaskAsDone(taskNumber - 1);
        storage.saveList(taskList.convertToFileFormat());
        return "Good work! Task is now marked as done:\n" + completedTask + "\n";
    }


}
