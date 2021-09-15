package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The type Update command.
 */
public class UpdateCommand extends Command {

    private final int idx;
    private final TaskList tasks;
    private final String newDescription;

    /**
     * Instantiates a new Update command.
     *
     * @param idx            the index of the task to update.
     * @param tasks          the list of tasks which contains the task to be updated.
     * @param newDescription the new description for the task.
     */
    public UpdateCommand(int idx, TaskList tasks, String newDescription) {
        assert tasks != null : "TaskList cannot be null.";
        assert newDescription != null : "Task description cannot be null.";

        this.idx = idx;
        this.tasks = tasks;
        this.newDescription = newDescription;
    }

    @Override
    public String execute() {
        Task current = tasks.get(idx);
        String result = "Your task:\n" + current.toString() + "\nhas been updated to: \n";
        current.updateDescription(newDescription);
        result += current.toString();
        return result;
    }
}
