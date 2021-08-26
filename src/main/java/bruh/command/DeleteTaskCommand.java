package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Represents a command which deletes a specified task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private final int indexToDelete;

    /**
     * Constructor for a command to delete a task from the task list.
     *
     * @param indexToDelete The index of the task to be deleted from the task list.
     */
    public DeleteTaskCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return String.format("Got it. I've removed this task:\n  %s\n%s", taskList.remove(indexToDelete).toString(),
                taskList.getTaskCountDesc());
    }
}
