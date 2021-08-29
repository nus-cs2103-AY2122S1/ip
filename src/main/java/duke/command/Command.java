package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

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
     * @return post execution message. 
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
