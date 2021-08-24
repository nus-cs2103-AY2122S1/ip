package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The list command.
 */
public class ListCommand extends Command {

    /**
     * Constructs the list command.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printToUser("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            ui.printToUser("  You currently have no tasks. Why not add a task?");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                ui.printToUser((i + 1) + ". " + currTask);
            }
        }
    }

    /**
     * Checks whether command terminate the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
