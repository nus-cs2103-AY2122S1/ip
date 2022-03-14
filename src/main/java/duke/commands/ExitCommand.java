package duke.commands;

/**
 * Represents the class that executes the command "Exit". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class ExitCommand extends Command {
    protected static final String USAGE = "bye\n\n";
    private static final String MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Retrieves the message stored after execution.
     *
     * @return message stored after execution
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
