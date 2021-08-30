package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to add a task to the task list.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
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
     */
    @Override
    public void runCommand() {
        taskList.addTask(task);
        ui.taskAddedMessage(task, taskList.size());
        storage.save(taskList.getList());
    }
}
