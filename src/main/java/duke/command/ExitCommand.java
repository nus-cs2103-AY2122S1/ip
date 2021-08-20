package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
        ui.showMessage(this.message(tasks));
        ui.exit();
    }

    @Override
    public String message(TaskList tasks) {
        return "Bye. Don't come again!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
