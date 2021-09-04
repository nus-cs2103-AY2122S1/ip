package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The find command.
 */
public class FindCommand extends Command {

    /**
     * The arguments associated with the command.
     **/
    private String arguments;

    /**
     * Constructs the find command.
     *
     * @param arguments The arguments associated with the command.
     */
    public FindCommand(String arguments) {
        super("find");
        this.arguments = arguments;
    }

    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     * @return The output of executing the command.
     * @throws DukeException If arguments are invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("No matching string was entered.");
        }

        TaskList matchedTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(arguments)) {
                matchedTasks.add(tasks.get(i));
            }
        }

        return "Here are the matching tasks in your list:" + matchedTasks;
    }

}
