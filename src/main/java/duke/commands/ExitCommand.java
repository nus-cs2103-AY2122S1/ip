package duke.commands;

import duke.exceptions.DukeFileException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * This is an duke.commands.ExitCommand class that extends duke.commands.Command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws DukeFileException {
        taskList.safeTasks(store);
        ui.bidFarewell();
    }
}
