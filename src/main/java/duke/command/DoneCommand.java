package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

public class DoneCommand extends Command {
    private int indexOfCompleted;

    public DoneCommand(int indexOfCompleted) {
        this.indexOfCompleted = indexOfCompleted;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.markAsDone(indexOfCompleted);
        storage.saveTasksToFile(taskList);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
