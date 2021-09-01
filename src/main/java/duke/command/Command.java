package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.TaskDoesNotExistException;
import duke.task.TaskList;

/**
 * Command class that encapsulates a user command.
 */
public abstract class Command {

    private boolean isExit;

    /**
     * Constructs a Command object.
     *
     * @param isExit True if the command terminates the app. Otherwise, false.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns true if the command terminates the app. Otherwise, false.
     *
     * @return True if the command terminates the app. Otherwise, false.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the given user command.
     *
     * @param tasks   List of Task objects.
     * @param ui      Ui object that user interacts with.
     * @param storage Storage object that stores and writes to file in hard disk.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws TaskDoesNotExistException;

}
