package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * This TagCommand class represents a command to tag a task in the task list.
 */
public class TagCommand extends Command {

    private int taskId;
    private String tagName;

    /**
     * Constructor for a TagCommand instance that takes in a task id and tag name.
     *
     * @param taskId The position of the task in the task list.
     * @param tagName The name of the tag given to the task.
     */
    public TagCommand(int taskId, String tagName) {
        this.taskId = taskId;
        this.tagName = tagName;
    }

    /**
     * Tags a task and updates the hard disk of the change.
     *
     * @param tasks The task list.
     * @param storage The storage system of the application.
     * @return Completion message of this command.
     * @throws TaskIndexOutOfBoundsException If the task list is accessed with an illegal index.
     * @throws IOException If the data cannot be saved in the file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskIndexOutOfBoundsException,
            IOException {
        Task taggedTask = tasks.tagTask(taskId, tagName);
        assert taggedTask != null : "The task should be tagged and returned accordingly";

        // Saves the updated task list to the hard drive
        storage.save(tasks);

        // Returns a message indicating the task has been successfully tagged
        return "Nice! I've tagged this task:\n  " + taggedTask.toString();
    }
}
