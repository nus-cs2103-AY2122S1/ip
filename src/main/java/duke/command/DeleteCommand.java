package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements ICommand {
    private final int lineIndex;

    public DeleteCommand(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.removeLine(lineIndex);
        Ui.printRemoveTask(taskList.get(lineIndex).toString());
        taskList.remove(lineIndex);
        Ui.printTaskCount(taskList.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            return ((DeleteCommand) o).lineIndex == this.lineIndex;
        }
        return false;
    }
}
