package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.gui.Ui;

/**
 * Encapsulates a command to be returned by Parser.parse() when it encounters an exception while parsing.
 */
public class ExceptionalCommand extends Command {
    private Exception e;

    /**
     * Constructs an ExceptionalCommand object.
     *
     * @param e The Exception encountered during parsing.
     */
    public ExceptionalCommand(Exception e) {
        this.e = e;
    }

    /**
     * Prints the detailed message from the exception encountered.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        String message = e.getMessage();
        Ui.formatAndPrint(message);
    }

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return false
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
