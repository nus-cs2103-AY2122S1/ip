package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Storage;
import duke.task.Task;

/**
 * Represents a Command that adds a Task to the TaskList.
 */
public class AddCommand extends Command {
    /**
     * The type of task to add to the task list.
     */
    TaskList.TaskType taskType;
    /**
     * The description of the task to add to the task list.
     */
    String taskDescription;

    /**
     * Constructs an add command with type of task and task description.
     *
     * @param taskType The type of task to add to the task list.
     * @param description The description of the task to add to the task list.
     */
    public AddCommand(TaskList.TaskType taskType, String description) {
        this.taskType = taskType;
        this.taskDescription = description;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks The task list to execute the command on.
     * @param ui The user interface.
     * @param storage The storage for the tasks.
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.addTask(taskType, taskDescription);

        ui.showMessage("I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.getListSize() + " tasks in the list.");

        storage.save(tasks.getListData());
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}