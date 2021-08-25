package duke.command;

import duke.data.Storage;
import duke.data.TaskList;

/**
 * Represents a command that stops the duke program.
 */
public class ExitCommand extends Command {
    /** Message to be printed when the program exits */
    private static final String EXITING_MESSAGE = "Bye. Hope to see you again soon!\n";

    /**
     * Constructor of the class `ExitCommand`.
     */
    public ExitCommand() {
        super("bye");
    }

    /**
     * Updates the message to be printed. Returns false because the program stops.
     *
     * @param taskList A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     * @return Whether the program is still running.
     */
    @Override
    public boolean execute(TaskList taskList, Storage storage) {
        this.message = ExitCommand.EXITING_MESSAGE;
        return false;
    }
}
