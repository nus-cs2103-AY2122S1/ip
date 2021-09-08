package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles task-listing command.
 */
public class ListCommand extends Command {

    // Appends task if the task is not null.
    private void appendToResponseIfNotNull(StringBuilder response, Task task, int currentIndex) {
        if (task != null) {
            String taskToAppend = String.format("\n %d.%s", currentIndex, task);
            response.append(taskToAppend);
        }
    }

    // Appends task to current response.
    private void appendToResponse(StringBuilder response, TaskList tasks, int currentIndex) {
        for (Task task : tasks.getTasks()) {
            appendToResponseIfNotNull(response, task, currentIndex);
            currentIndex++;
        }
    }

    /**
     * Returns the response after executing the task-listing command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        int currentIndex = 1;
        appendToResponse(response, tasks, currentIndex);

        return response.toString();
    }

    /**
     * Returns the boolean false since it is not a command that exits the program.
     *
     * @return The boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
