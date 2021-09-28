package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.TaskOutOfRangeException;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNo < 1 || taskNo > tasks.getLength()) {
            throw new TaskOutOfRangeException(tasks.getLength());
        }
        Task deletedTask = tasks.getTask(this.taskNo - 1);
        tasks.deleteTask(this.taskNo);
        storage.rewriteTaskRecord(tasks);
        return ui.showDeleted(deletedTask, tasks.getLength());
    }
}
