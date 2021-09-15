package commands;
import exceptions.MorganException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;

/**
 * This is an abstract AddCommand Class, which inherits from Command.
 * This class abstracts the execution() method of its subclasses.
 * The execution of this command will add a task to the task list.
 */
public abstract class AddCommand extends Command {
    protected Task task;

    /**
     * Add a task to the task list.
     * @param tasks The existing list where the task will be added to.
     * @param storage The storage object to store task data.
     * @return The completion message after execution.
     * @throws MorganException If input format is invalid.
     */
    public String execute(TaskList tasks, Storage storage) throws MorganException {
        assert tasks != null && storage != null;
        tasks.addTask(task);
        int numTasks = tasks.getNumOfTasks();
        storage.save(tasks);
        return "Got it. I've added this task:\n\t" + this.task
                + "\nNow you have " + numTasks + " tasks in the list.";
    }
}
