package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * This class encapsulates the add task command.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Constructor of the AddTaskCommand class.
     *
     * @param task The task to be added.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command by adding the task into the in-memory list, and saving it to the txt file.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke app.
     * @param storage The storage manager for the Duke app.
     * @throws IOException Unexpected IO exception when trying to append tasks to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(this.task);
        storage.append(this.task);
        ui.printTaskAdded(this.task);
        ui.printNumOfTasks(tasks);
    }
}
