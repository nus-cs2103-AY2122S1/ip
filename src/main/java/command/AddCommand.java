package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import task.Task;

/**
 * Command to add a Task.
 *
 * @author felix-ong
 */
public class AddCommand extends Command {
    private Task task;
    private String taskCountMessage;

    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Executes the specific actions for this command.
     *
     * @param tasks Handles the list of tasks.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(storage, this.task);
        this.taskCountMessage = tasks.getTaskCount();
    }

    /**
     * Returns false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Added task: %n %s%n%s", task, taskCountMessage);
    }
}
