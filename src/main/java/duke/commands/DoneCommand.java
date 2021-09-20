package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents Command to mark a Task in a TaskList as done.
 */
public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the Task in a TaskList with the index stored in the DoneCommand's taskIndex as done.
     *
     * @param tasks TaskList where the Task should be marked as done.
     * @param ui Ui that will display the messages to user when a Task is marked as done.
     * @param storage Storage where the TaskList should be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markTaskDone(taskIndex);
        Storage.saveTasks(tasks);
        return ui.showMarkDone(task);
    }

    /**
     * Checks if an object is equal to this DoneCommand.
     * Returns true if object is an DoneCommand with the same taskIndex to be deleted.
     *
     * @param obj Object to be compared to this DoneCommand.
     * @return True if obj is equal to this DoneCommand, else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoneCommand) {
            DoneCommand other = (DoneCommand) obj;
            return taskIndex == other.taskIndex;
        }
        return false;
    }
}
