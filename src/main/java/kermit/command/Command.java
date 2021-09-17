package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Response;

/**
 * Represents a command that Kermit can execute.
 */
public abstract class Command {
    /**
     * Executes command and process action.
     *
     * @param taskList Instance of task list used.
     * @param response Instance of Ui used.
     * @param storage Instance of storage class used.
     * @throws KermitException if there are any errors while performing action.
     */
    public abstract String execute(TaskList taskList, Response response, Storage storage) throws KermitException;

    /**
     * Returns boolean if isExit command.
     *
     * @return true if it is an instanceOf Exit, otherwise returns false
     */
    public abstract boolean isExit();
}
