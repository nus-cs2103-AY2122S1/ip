package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * This class handles command that mark certain task as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private String cmd;

    /**
     * Constructor for DoneCommand.
     * @param input
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
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int id = Integer.parseInt(cmd.strip());
            Task task = tasks.get(id);
            task.markAsDone();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}