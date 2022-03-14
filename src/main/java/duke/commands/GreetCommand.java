package duke.commands;

/**
 * Represents the class that executes the command "Greet". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class GreetCommand extends Command {
    private static final String MESSAGE = "Hello! I'm Duke. What can I do for you?";

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
