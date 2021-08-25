package duke.command;

import duke.data.Storage;
import duke.data.TaskList;

/**
 * Represents a processor that stops the duke program.
 */
public class ExitCommand extends Command {
    /** Message to be printed when the program exits */
    private static final String EXITING_MESSAGE = "Bye. Hope to see you again soon!\n";

    /**
     * Constructor of the class `ExitProcessor`.
     */
    public ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        this.message = ExitCommand.EXITING_MESSAGE;
    }

    /**
     * Returns false because the duke program stops.
     *
     * @return Whether the program is running.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
