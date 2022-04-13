package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

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
     * Returns the detailed message from the exception encountered.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The detailed message from the exception encountered.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return e.getMessage();
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
