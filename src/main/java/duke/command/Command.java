package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Each function of the application is implemented by creating a class that extends Command. That class overrides
 * execute to achieve its specific task. Common features to all Commands implemented here.
 */
public abstract class Command {
    protected boolean isDone = false;

    public abstract void execute(TaskList tasklist, Ui ui, Storage store);

    /**
     * This method is just for control purposes, to indicate when the application should exit
     * @return Boolean that is true when the application should exit.
     */
    public boolean isExit() {
        return isDone;
    }
}
