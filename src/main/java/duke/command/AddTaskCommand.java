package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.task.Task;

/**
 * Represents a processor that can add a task to the task list. A subclass of the Processor class.
 */
public class AddTaskCommand extends Command {
    /**
     * Constructor of the class `AddATaskProcessor`.
     *
     * @param task Task to be added.
     */
    public AddTaskCommand(Task task) {
        super("add");
        this.task = task;
    }

    /**
     * Adds a task into the list and updates the message to be printed.
     *
     * @return Whether the program is still running.
     */
    @Override
    public boolean execute(TaskList taskList, Storage storage) {
        taskList.addTask(this.task);
        storage.addToFile(this.task);
        this.message = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %o tasks in the list.\n",
                this.task, taskList.getNumOfTasks());
        return true;
    }
}
