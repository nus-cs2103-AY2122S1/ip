package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ExitCommand implements ICommand {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IllegalArgumentException {
        if (taskList == null || ui == null || storage == null) {
            throw new IllegalArgumentException("One of the parameters is null.");
        }
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
