package workdone.command;

import workdone.data.Storage;
import workdone.data.TaskList;

/**
 * Represents a command that stops the WorkDone program.
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
     * @param tasks A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        this.message = ExitCommand.EXITING_MESSAGE;
    }

    /**
     * Returns false because the WorkDone program stops.
     *
     * @return Whether the program is running.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
