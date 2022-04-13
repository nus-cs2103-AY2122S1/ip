package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a ListCommand that will output the list of tasks when executed.
 *
 * @author ruiquan
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the ListCommand and list out all the tasks in the TaskList.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @return The message representing the response.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String message = String.format("Here are the %s in your list:\n%s",
                tasks.size() <= 1 ? "task" : "tasks", tasks);
        return message;
    }
}
