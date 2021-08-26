package duke.command;

import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Base Command Class for the other Command classes
 */
public class Command {
    private final boolean isRunning;

    /**
     * Constructor for the command class
     *
     * @param isRunning boolean to indicate if the bot still runs after this function
     */
    public Command(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Checks if the bot still runs after this command
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Executes the command
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     * @throws IOException   if the filepath has any issues
     * @throws DukeException to handle any other input/format error
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
    }
}

