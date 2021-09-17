package duke.command;

/**
 * Class that represents the Command to exit from the program.
 *
 * @author Benedict Chua
 */
public class ExitCommand extends Command {
    public ExitCommand() { }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * This executes the exit command.
     */
    @Override
    public String execute() {
        return "Hmph! It's not like I want to see you again or anything!";
    }
}
