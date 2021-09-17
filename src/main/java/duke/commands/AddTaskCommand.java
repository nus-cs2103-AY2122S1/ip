package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a command to add a Task to a TaskList.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddTaskCommand.
     *
     * @param task Task to be added to a TaskList.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the Task stored in the AddTaskCommand to a TaskList.
     *
     * @param tasks TaskList where the Task should be added.
     * @param ui Ui that will display the messages to user when a Task is added.
     * @param storage Storage where the TaskList should be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addNewTask(task);
        Storage.saveTasks(tasks);
        return ui.showAddTask(tasks, task);
    }

    /**
     * Checks if an object is equal to this AddTaskCommand.
     * Returns true if object is an AddTaskCommand with the same Task to be added.
     *
     * @param obj Object to be compared to this AddTaskCommand.
     * @return True if obj is equal to this AddTaskCommand, else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddTaskCommand) {
            AddTaskCommand otherTask = (AddTaskCommand) obj;
            return task.equals(otherTask.task);
        }
        return false;
    }
}
