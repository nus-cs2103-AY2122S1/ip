package duke.commands;

/**
 * Represents the class that executes the command "Help". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class HelpCommand extends Command {
    public static final String MESSAGE = "List of available commands:\n\n"
            + AddCommand.USAGE
            + DeleteCommand.USAGE
            + DoneCommand.USAGE
            + ExitCommand.USAGE
            + FindCommand.USAGE
            + ListTasksCommand.USAGE;

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
