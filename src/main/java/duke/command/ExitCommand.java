package duke.command;

import static duke.Ui.dukePrint;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Represents command to exit loop.
 */
public class ExitCommand implements Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return dukePrint("Bye. Hope to see you again soon!\n");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
