package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final Task task;
    private final int index;

    public DeleteCommand(Task task, int index) {
        this.task = task;
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) throws DukeException {
        tasks.delete(this.index);
        ui.deleteTask(tasks.getTask(this.index));
        fileManager.updateTaskList(tasks, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
