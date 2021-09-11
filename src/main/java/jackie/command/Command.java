package jackie.command;

/**
 * An abstract class that requires the subclasses to implement methods of execute and isExit.
 *
 * @author Gu Geng
 */
public abstract class Command {
    public abstract String execute(jackie.TaskList taskList, jackie.Ui ui, jackie.Storage storage)
            throws jackie.JackieException;
    public abstract boolean isExit();
}
