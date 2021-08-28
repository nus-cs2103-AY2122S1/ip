package duke.command;

/**
 * An abstract class that requires the subclasses to implement methods of execute and isExit.
 *
 * @author Gu Geng
 */
public abstract class Command {
    public abstract void execute(duke.TaskList taskList, duke.Ui ui, duke.Storage storage) throws duke.DukeException;
    public abstract boolean isExit();
}
