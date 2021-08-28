package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The delete command.
 */
public class DeleteCommand extends Command {

    /**
     * The arguments associated with the command.
     **/
    private String arguments;

    /**
     * Constructs the delete command.
     *
     * @param arguments The arguments associated with the command.
     */
    public DeleteCommand(String arguments) {
        super("delete");
        this.arguments = arguments;
    }

    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     * @throws DukeException If arguments are invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("No index was keyed in. Please try again.");
        }

        int index = Integer.parseInt(arguments);
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("The index you entered is invalid. Please try again.");
        }

        Task taskDeleted = tasks.remove(index - 1);
        ui.printToUser("Noted! I've removed this task:");
        ui.printToUser("  " + taskDeleted);
        ui.printToUser("Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.");
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
