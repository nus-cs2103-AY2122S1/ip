package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Gui;


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
     * @param taskList The task list to execute the command on.
     * @param gui The user interface to display the reply.
     * @param storage The place to store the session.
     */

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.exit();
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
