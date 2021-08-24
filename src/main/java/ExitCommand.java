/**
 * An ExitCommand class that extends from the Command class.
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class ExitCommand extends Command{

    /**
     * An empty constructor to initialize an exit command.
     */
    public ExitCommand() {
    }

    /**
     * a method to execute a command.
     * @param taskList The task list to execute the command on
     * @param ui The user interface to display the reply
     * @param storage The place to store the session
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.exit();
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
