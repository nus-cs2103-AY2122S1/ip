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
     * @param taskList The existing list where the task will be added to.
     * @return The completion message after execution.
     */
    public String execute(TaskList taskList, Storage storage) throws MorganException {
        taskList.addTask(task);
        int numTasks = taskList.getNumOfTasks();
        try {
            storage.save(taskList);
        } catch (MorganException e) {
            throw e;
        }
        return "Got it. I've added this task:\n\t" + this.task
                + "\nNow you have " + numTasks + " tasks in the list.";
    }
}