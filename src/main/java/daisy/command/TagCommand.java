package daisy.command;

import daisy.DaisyException;
import daisy.task.Storage;
import daisy.task.Task;
import daisy.task.TaskList;

/**
 * TagCommand class handles the 'tag' command to add tags to a task.
 */
public class TagCommand extends Command {
    static final String TAG_HEADER = "Got it! Tags are added to task:\n";
    private String[] tags;
    private int taskNumber;

    /**
     * Constructs the TagCommand object.
     *
     * @param taskNumber Task number of task to add tags to.
     * @param tags Tags to be added to task.
     */
    public TagCommand(int taskNumber, String[] tags) {
        this.taskNumber = taskNumber;
        this.tags = tags;
    }
    /**
     * Returns the response string after adding tags to a task.
     *
     * @param taskList The TaskList of Daisy.
     * @param storage The Storage of Daisy.
     * @return Response string.
     * @throws DaisyException  If taskNumber is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DaisyException {
        Task task = taskList.addTags(taskNumber - 1, tags);
        storage.saveList(taskList.convertToFileFormat());
        return TAG_HEADER + task;
    }
}
