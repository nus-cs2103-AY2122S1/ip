package duke.command;
import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * This class handles command that deletes task from list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private String cmd;

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
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int id = Integer.parseInt(cmd.strip());
            taskList.delete(id);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}