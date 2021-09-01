package IP.duke.command;

import IP.duke.main.DukeException;
import IP.duke.main.Storage;
import IP.duke.main.TaskList;
import IP.duke.main.Ui;

/**
 * Represents a command given by the user.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public abstract class Command {

    private boolean isExitCommand;

    /**
     * Class constructor. 
     */
    public Command() {
        isExitCommand = false;
    }

    /**
     * Executes the given command.
     * 
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException Class
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether the command is an exit command.
     * 
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExitCommand;
    }
    
}
