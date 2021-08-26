package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Todo;

/**
 * Encapsulates a command that handles the addition of todo tasks into the task list.
 */
public class TodoCommand extends Command {
    private Todo todoTask;

    /**
     * Constructs a TodoCommand object.
     *
     * @param taskName Description of the todo task.
     */
    public TodoCommand(String taskName) {
        todoTask = new Todo(taskName);
    }

    /**
     * Adds the todo task into the task list and storage file.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(todoTask);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return false
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
