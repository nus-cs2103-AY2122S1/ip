package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkTaskDoneCommand extends Command {
    private final int indexToMarkDone;

    /**
     * Constructor for a command to mark a task as done.
     *
     * @param indexToMarkDone The index of the task to be mark done.
     */
    public MarkTaskDoneCommand(int indexToMarkDone) {
        this.indexToMarkDone = indexToMarkDone;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return String.format("Nice! I've marked this task as done:\n  %s",
                taskList.markAsDone(indexToMarkDone).toString());
    }
}
