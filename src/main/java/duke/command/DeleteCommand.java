package duke.command;

import duke.exception.DukeException;
import duke.util.Tasklist;

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
    private Tasklist list;
    private int toDelete;

    /**
     * Constructor for DeleteCommand.
     * Takes in the task number to delete and creates a delete command
     */
    public DeleteCommand(Tasklist list, int toDelete) {

        this.list = list;
        this.toDelete = toDelete;

    }

    /**
     * Executes the delete command and delete the inputted task to the task list
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {

        String successMessage = this.list.deleteTask(toDelete);
        return successMessage;

    }
}
