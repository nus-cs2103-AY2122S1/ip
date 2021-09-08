package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class DeleteAllCommand implements ICommand {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteAll();
        storage.removeAll();
        Ui.printRemoveAll();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == this.getClass();
    }
}
