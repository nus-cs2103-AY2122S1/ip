package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Each function of the application is implemented by creating a class that extends Command. That class overrides
 * execute to achieve its specific task. Common features to all Commands implemented here.
 */
public abstract class Command {
    public abstract String execute(TaskList tasklist, Ui ui, Storage store);
}
