package duke.commands;

import duke.exceptions.InvalidTaskIdException;
import duke.utils.TaskList;

/**
 * Encapsulates a command to delete a task from the task list.
 */
public class Delete extends Command {
    private int index;

    /**
     * Creates a command that will delete a task, specified by the
     * task index.
     *
     * @param index Index of the task to be deleted.
     */
    public Delete(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList) throws InvalidTaskIdException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new InvalidTaskIdException();
        }
        taskList.delete(index - 1);
        return taskList;
    }
}
