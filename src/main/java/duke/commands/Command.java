package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Storage storage;

    public void setData(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }


}
