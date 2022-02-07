package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.OutOfBoundsException;
import duke.task.Task;

/**
 * This class handles command that mark certain task as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private final String cmd;

    /**
     * Constructor for DoneCommand.
     * @param input Reference to the task done.
     */
    public DoneCommand(String input) {
        this.cmd = input;
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return Response on marking a task as done.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int id;
        try {
            id = Integer.parseInt(cmd.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }

        try {
            Task task = tasks.get(id);
            return task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsException();
        }
    }
}
