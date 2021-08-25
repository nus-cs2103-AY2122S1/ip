package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.UI;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, FileManager fileManager) throws DukeException {
        Task removedTask = tasks.getTask(this.index);
        tasks.delete(this.index);
        ui.deleteTask(removedTask);
        fileManager.updateTaskList(tasks, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
