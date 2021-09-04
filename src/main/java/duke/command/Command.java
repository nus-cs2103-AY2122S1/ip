package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.data.Ui;

/**
 * An abstract command that contains an execute and isExit method.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

}
