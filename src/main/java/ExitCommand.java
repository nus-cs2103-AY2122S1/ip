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
    public boolean execute(TaskList taskList, Storage storage) {
        this.message = ExitCommand.EXITING_MESSAGE;
        return false;
    }
}
