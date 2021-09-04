package duke.commands;

import duke.exceptions.DukeFileException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is an ExitCommand class that extends Command.
 * This class returns the farewell message to CLI users.
 */
public class ExitCommand extends Command {

    /**
     * This is an ExitCommand Constructor.
     */
    public ExitCommand() {
        super("bye");
    }

    @Override
    public String execute(TaskList taskList, Storage store, Ui ui) throws DukeFileException {
        taskList.safeTasks(store);
        return ui.bidFarewell();
    }

    @Override
    public String toString() {
        return this.command;
    }
}
