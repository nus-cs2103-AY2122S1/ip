package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Todo;

import java.io.IOException;

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
     * Adds the todo task into the task list and storage file and returns the output to be displayed by Duke.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The output to be displayed by Duke.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String output = taskList.addTask(todoTask);
        try {
            storage.saveTasksToFile(taskList);
        } catch (IOException e) {
            output = String.format("%s\n%s", output, e.getMessage());
        }

        return output;
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
