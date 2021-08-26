package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeFileException;

/**
 * This is an ExitCommand class that extends Command.
 */
public class ExitCommand extends Command {

    /**
     * This is an ExitCommand Constructor.
     */
    public ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList taskList, Storage store, Ui ui) throws DukeFileException {
        taskList.safeTasks(store);
        ui.bidFarewell();
    }

    @Override
    public String toString() {
        return this.command;
    }
}
