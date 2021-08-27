package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class handles command that stops the programme.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Used for the main to know that the programme should stop.
     *
     * @return False.
     */
    @Override
    public boolean isExist() {
        return false;
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        storage.save(tasks.getList());
    }
}