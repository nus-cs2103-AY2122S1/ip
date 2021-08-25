package command;

import core.Storage;
import core.TaskList;

public class DoneCommand extends Command {
    private int indexOfCompleted;

    public DoneCommand(int indexOfCompleted) {
        this.indexOfCompleted = indexOfCompleted;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.markAsDone(indexOfCompleted);
        storage.addTasksToFile(taskList);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
