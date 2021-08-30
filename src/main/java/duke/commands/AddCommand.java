package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Response;

/**
 * The AddCommand class extends the Command class and is the Command that
 * adds a Task to the TaskList.
 */
public class AddCommand extends Command {

    /** The Task being added by the Command. */
    private Task taskToAdd;

    /**
     * The constructor for the AddCommand object.
     *
     * @param task The Task to be added to the TaskList
     */
    public AddCommand(Task task) {
        super(CommandType.ADD, false);
        this.taskToAdd = task;
    }

    /**
     * The execute command that executes the necessary actions when a
     * Task is added to the TaskList.
     *
     * @param tasks The TaskList to be added to
     * @param response The Ui object to interact with the user
     * @param storage The Storage object that stores the TaskList on the Local Machine
     */
    public String execute(TaskList tasks, Response response, Storage storage) {
        storage.addToStorage(taskToAdd);
        tasks.addToTaskList(taskToAdd);
        return response.showAddTaskMessage(taskToAdd, tasks);
    }
}
