package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.task.Task;

/**
 * Represents a command that adds a task to the task list. A subclass of the Command class.
 */
public class AddTaskCommand extends Command {
    /**
     * Constructor of the class `AddTaskCommand`.
     *
     * @param task Task to be added.
     */
    public AddTaskCommand(Task task) {
        super("add");
        this.task = task;
    }

    /**
     * Executes the command. Adds a task into the list, stores it and updates the message to be printed.
     *
     * @param tasks A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        // Add the task
        tasks.addTask(this.task);
        storage.addToFile(this.task);

        // Update message
        this.message = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %o tasks in the list.\n",
                this.task, tasks.getNumOfTasks());
    }
}
