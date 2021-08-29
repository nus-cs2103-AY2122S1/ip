package duke.command;

import duke.exception.DukeException;
import duke.util.Store;
import duke.util.Tasklist;
import duke.util.Ui;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates the delete task command inputted by the user
 *
 * @author Keith Tan
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int toDelete;

    /**
     * Constructor for DeleteCommand.
     * Takes in the task number to delete and creates a delete command
     */
    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    /**
     * Executes the delete command and delete the inputted task to the task list
     *
     * @param list Tasklist of current tasks
     * @param ui Ui which prints any successful message from the associated methods
     * @param storage Current Storage of DUke
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {
        String successMessage = list.deleteTask(toDelete);
        ui.printMessage(successMessage);

    }
}
