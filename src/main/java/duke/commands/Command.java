package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;

/**
 * Abstract class for executing Commands.
 */
public abstract class Command {
    protected Storage storage;
    protected TaskList taskList;
    protected String[] strParse;
    protected boolean isActivatedClearCommand;

    public Command(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        this.storage = storage;
        this.taskList = taskList;
        this.strParse = strParse;
        this.isActivatedClearCommand = isActivatedClearCommand;
    }

    public abstract String execute();

    /**
     * Getter method for error message.
     *
     * @param e DukeException thrown
     * @return Error message of DukeException
     */
    public String getErrorMessage(DukeException e) {
        return e.getMessage();
    }

}
