package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.ui.Ui;
import duke.storage.Storage;

public class DoneCommand extends Command{
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task currTask = TaskList.get(index);
        currTask.markAsDone();
        ui.doneTask(currTask);
    }
}
