package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private int taskNo;
    
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.getTask(this.taskNo - 1);
        tasks.deleteTask(this.taskNo);
        storage.rewriteTaskRecord(tasks);
        ui.showDeleted(deletedTask, tasks.getLength());
    }
}
