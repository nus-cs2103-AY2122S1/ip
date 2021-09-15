package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command abstract class that contains basic structure of a command
 */
public abstract class Command {

    protected final Storage storage;
    protected final TaskList taskList;
    protected boolean isBye;

    /**
     * Basic Constructor
     *
     * @param storage StorageTxt object to save
     * @param taskList Tasklist to add task to
     * @param isBye boolean to check whether to end the program
     */
    public Command(Storage storage, TaskList taskList, boolean isBye) {
        this.storage = storage;
        this.taskList = taskList;
        this.isBye = isBye;
    }

    /**
     * Checks command closes the application
     *
     * @return
     */
    public boolean checkIsBye() {
        return this.isBye;
    }

    /**
     * Abstract method that is to be defined in subclasses of Command
     *
     * @return boolean To indicate whether to keep looking for the next user input
     * @throws DukeException
     */
    public abstract String exec() throws DukeException;
}
