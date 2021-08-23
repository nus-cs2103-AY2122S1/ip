package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EndCommand implements Command{
    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.textFrame("Goodbye from Bob's list adder!");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
