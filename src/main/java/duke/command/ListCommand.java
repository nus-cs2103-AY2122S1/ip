package duke.command;

import duke.exception.EmptyListException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the list command.
 */
public class ListCommand extends Command {

    /** Represents the list command keyword. */
    public static final String COMMAND = "list";

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return all tasks in the lists printed out.
     * @throws EmptyListException if the task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException {
        return tasks.displayList();
    }
}
