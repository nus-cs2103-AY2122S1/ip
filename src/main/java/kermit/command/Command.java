package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.ToDo;
import kermit.Ui;

/**
 * Represents a command that Kermit can execute.
 */
public abstract class Command {

    /**
     * Execute command and process action.
     *
     * @param taskList Instance of task list used.
     * @param ui Instance of Ui used.
     * @param storage Instance of storage class used.
     * @throws KermitException if there are any errors while performing action.
     */
    public abstract void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException;

    /**
     * If command is exit command
     *
     * @return true if it is an instanceOf Exit, otherwise returns false
     */
    public abstract boolean isExit();
}
