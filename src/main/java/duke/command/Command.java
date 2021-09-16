package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Stores description of usage and format of command.
 */
public abstract class Command {

    /**
     * Constructs Command object
     */
    public Command() {}

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
