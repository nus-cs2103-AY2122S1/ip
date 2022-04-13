package duke.commands;

import duke.exceptions.InvalidTaskIdException;
import duke.utils.TaskList;

/**
 * Encapsulates a command to mark a task in the task list as done.
 */
public class Done extends Command {
    private int index;

    /**
     * Creates a command to mark a task as done. The task is specified
     * by the given task index.
     *
     * @param index Index of the task to be marked as done.
     */
    public Done(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList) throws InvalidTaskIdException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new InvalidTaskIdException();
        }
        taskList.get(index - 1).markAsCompleted();
        return taskList;
    }
}
