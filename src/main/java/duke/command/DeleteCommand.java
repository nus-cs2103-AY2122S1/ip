package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.delete(indexToDelete);
        storage.saveTasksToFile(taskList);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}