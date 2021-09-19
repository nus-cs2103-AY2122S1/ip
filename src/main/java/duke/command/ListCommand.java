package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Represents a command which lists the current tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Returns the current tasks in the list.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @return The execution result.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        String list = "";

        for (int i = 0; i < tasks.getSize(); i++) {
            list += String.format("%d.%s\n", i + 1, tasks.getTask(i));
        }

        return ui.listResponse(list);
    }
}
