/**
 * An abstract class representing a command
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
