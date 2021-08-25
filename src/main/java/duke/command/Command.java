package duke.command;

/**
 * Represents an executable command made by the user.
 *
 * @author Aiken Wong
 */
public abstract class Command {

    /**
     * Executes the command, invoking Duke to do something.
     */
    public void execute() {
    }

    /**
     * Return information on whether the command should cause and exit for the Duke app.
     *
     * @return Information on whether to exit.
     */
    public boolean isExit() {
        return false;
    }
}
