package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.OutOfBoundsException;

/**
 * This class handles command that deletes task from list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final String cmd;

    /**
     * Constructor for command that deletes tasks.
     *
     * @param input String input.
     */
    public DeleteCommand(String input) {
        this.cmd = input;
    }

    /**
     * Method to execute command.
     *
     * @param taskList The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return Response message of deleting a task.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int id;
        String result;
        try {
            id = Integer.parseInt(cmd.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }

        try {
            result = taskList.delete(id);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsException();
        }
        return result;
    }
}
