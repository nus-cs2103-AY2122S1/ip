package duke.command;

/**
 * Command is an abstract class that encapsulates the behaviour of the ability to do something.
 *
 * @author leezhixuan
 */
public abstract class Command {

    /**
     * Executes the appropriate action.
     *
     * @return status message
     */
    public abstract String execute();
}
