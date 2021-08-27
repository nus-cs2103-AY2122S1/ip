package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;

public class ExitCommand  extends Command {
    public ExitCommand() {
        this.isExit = true;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printOutput("Bye. Hope to see you again soon!");
    }
}
