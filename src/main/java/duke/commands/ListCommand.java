package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
     * @param ui The user interface that handles input and output.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = String.format("Here are the %s in your list:\n%s",
                tasks.size() <= 1 ? "task" : "tasks", tasks);
        return message;
    }
}
