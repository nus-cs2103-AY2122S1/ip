package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.task.Storage;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye bye! See you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
