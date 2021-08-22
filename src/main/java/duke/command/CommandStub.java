package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class CommandStub extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(message(tasks));
    }

    @Override
    public String message(TaskList tasks) {
        return "Stub message";
    }
}
