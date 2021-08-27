package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command abstract class that contains basic structure of a command
 */
public abstract class Command {

    protected final Storage storage;
    protected final TaskList taskList;
    protected final Ui ui;

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param ui Ui to display msg
     */
    public Command(Storage storage,TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Abstract method that is to be defined in subclasses of Command
     *
     * @return boolean To indicate whether to keep looking for the next user input
     * @throws DukeException
     */
    public abstract boolean exec() throws DukeException;
}
