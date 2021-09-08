package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * OnCommand class handles the 'on' command to list the tasks on a date.
 */
public class TagCommand extends Command {
    static final String TAG_HEADER = "Got it! Tags are added to task:\n";
    private String[] tags;
    private int taskNumber;

    /**
     * Constructs the OnCommand object.
     *
     * @param tags Date queried for the 'on' command.
     */
    public TagCommand(int taskNumber, String[] tags) {
        this.taskNumber = taskNumber;
        this.tags = tags;
    }
    /**
     * Returns the tasks the with same date in a response string.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If taskNumber is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.addTags(taskNumber - 1, tags);
        storage.saveList(taskList.convertToFileFormat());
        return TAG_HEADER + task;
    }
}
