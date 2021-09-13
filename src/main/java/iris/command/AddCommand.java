package iris.command;

import iris.storage.Storage;
import iris.task.Task;
import iris.task.TaskList;
import iris.ui.Ui;

/**
 * Command to add a task to the task list.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructor for an AddCommand.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     * @param task     Task to be added to task list.
     */
    public AddCommand(TaskList taskList, Storage storage, Ui ui, Task task) {
        super(taskList, storage, ui);
        this.task = task;
    }

    /**
     * Executes runCommand. Adds the task to the task list,
     * prints a message to tell user that task has been added
     * and update local data file.
     *
     * @return String representation when Iris successfully adds a task.
     */
    @Override
    public String runCommand() {
        assert task != null : "There is no task.";
        taskList.addTask(task);
        storage.save(taskList.getList());
        return ui.guiTaskAddedMessage(task, taskList.size());
    }
}
