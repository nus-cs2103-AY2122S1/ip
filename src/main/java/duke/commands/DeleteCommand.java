package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents Command to delete a Task from a TaskList.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the given taskIndex
     *
     * @param taskIndex Index of Task in TaskList to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the Task in a TaskList with the index stored in the DeleteCommand's taskIndex.
     *
     * @param tasks TaskList where the Task should be deleted.
     * @param ui Ui that will display the messages to user when a Task is deleted.
     * @param storage Storage where the TaskList should be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskIndex);
        storage.saveTasks(tasks);
        return ui.showDelete(task, tasks);
    }

    /**
     * Checks if an object is equal to this DeleteCommand.
     * Returns true if object is an DeleteCommand with the same taskIndex to be deleted.
     *
     * @param obj Object to be compared to this DeleteCommand.
     * @return True if obj is equal to this DeleteCommand, else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            return (taskIndex == other.taskIndex);
        }
        return false;
    }
}
