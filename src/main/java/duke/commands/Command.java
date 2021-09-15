package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;


/**
 * Abstract class that represents a Command in the application
 */
public abstract class Command {
    /**
     * Executes the command's behaviour by performing the steps specified.
     *
     * @param taskList TaskList object that is being used in the app
     * @param ui the Ui object that is being used in the app
     * @param storage the Storage object that is being used in the app
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Returns whether or not this command is an exit command
     *
     * @return boolean whether this is an exit command
     */
    public abstract boolean isExit();

}
