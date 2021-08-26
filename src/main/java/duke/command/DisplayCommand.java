package duke.command;

import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * This class handles command that requires all tasks to be displayed.
 */
public class DisplayCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructor for Display Command.
     */
    public DisplayCommand() {
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
        tasks.display();
    }
}