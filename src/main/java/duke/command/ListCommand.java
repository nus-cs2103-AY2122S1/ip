package duke.command;

import duke.Storage;
import duke.ui.Ui;
import duke.TaskList;

public class ListCommand implements ICommand{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.printList(taskList);
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
