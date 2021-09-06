package duke.command;

import duke.Storage;
import duke.ui.Ui;
import duke.TaskList;

public class ExitCommand implements ICommand{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == this.getClass();
    }
}
