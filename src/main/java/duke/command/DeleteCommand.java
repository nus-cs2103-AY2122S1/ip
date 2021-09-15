package duke.command;

import duke.task.TaskList;

/**
 * The type Delete command that deletes a user-specified tasks from a given list of tasks.
 */
public class DeleteCommand extends Command {

    /** User inputted task number to delete. */
    private final int idx;
    /** List of tasks to run the command on. */
    private final TaskList tasks;

    /**
     * Instantiates a new Delete command.
     *
     * @param idx   the index of task to be deleted.
     * @param tasks list of tasks to delete the user-specified task from.
     */
    public DeleteCommand(int idx, TaskList tasks) {
        assert tasks != null : "TaskList cannot be null.";
        this.idx = idx;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        String result = "Noted. I've removed this task:\n  " + tasks.get(idx).toString() + "\n";
        tasks.delete(idx);
        result += "Now you have " + tasks.size() + " tasks in the list.";
        return result;
    }

}
