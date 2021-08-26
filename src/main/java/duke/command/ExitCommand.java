package duke.command;

import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye bye! See you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
