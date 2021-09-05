package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.task.Task;

public class DoneCommand implements ICommand{
    private int lineIndex;

    public DoneCommand(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(lineIndex);
        storage.setLine(lineIndex, task.populateSaveData());
        task.setStatus(true);
        Ui.printMarkDone(task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
