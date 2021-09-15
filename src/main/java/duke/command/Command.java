package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class encapsulates an abstract Command class, to be implemented as specific commands for Duke.
 */
public abstract class Command {

    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    /**
     * Constructor for a Command.
     * @param taskList TaskList used by Duke instance.
     * @param ui Ui used by the DUke instance.
     */
    public Command(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    public abstract void execute();

}
