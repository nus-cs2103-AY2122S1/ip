package duke.command;

import duke.task.TaskList;

/**
 * The type Done command that marks a user-specified task as done from a given list of tasks.
 */
public class DoneCommand extends Command {

    /** User inputted task number to mark as done. */
    private final int idx;
    /** List of tasks to run command on. */
    private final TaskList tasks;

    /**
     * Instantiates a new Done command.
     *
     * @param idx       index of task to be marked done.
     * @param tasks     list of tasks to mark a task done from.
     */

    public DoneCommand(int idx, TaskList tasks) {
        assert tasks != null : "TaskList cannot be null.";
        this.idx = idx;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        tasks.markDone(idx);
        String result = "Nice! I've marked this task as done:\n  "
                + tasks.get(idx).toString();
        return result;
    }
}
