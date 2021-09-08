package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand implements ICommand {
    private final int lineIndex;

    public DoneCommand(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IllegalArgumentException {
        if (taskList == null || ui == null || storage == null) {
            throw new IllegalArgumentException("One of the parameters is null.");
        }
        Task task = taskList.get(lineIndex);
        storage.setLine(lineIndex, task.populateSaveData());
        task.setStatus(true);
        Ui.printMarkDone(task.toString());
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
            return ((DoneCommand) o).lineIndex == this.lineIndex;
        }
        return false;
    }
}
