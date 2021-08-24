package duke.command;

import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

/**
 * Command to print out the Tasklist to the user
 */
public class ListCommand extends Command {

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) {
        ui.showList(tasks);
    }
}
