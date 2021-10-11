package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.TaskOutOfRangeException;
import duke.tasks.Task;

public class MarkDoneCommand extends Command {
    private int taskNo;

    public MarkDoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNo < 1 || taskNo > tasks.getLength()) {
            throw new TaskOutOfRangeException(tasks.getLength());
        }
        Task task = tasks.getTask(this.taskNo - 1);
        if (task.getCompletionStatus()) {
            return ui.showTaskDone(task);
        }
        tasks.taskDone(this.taskNo);
        storage.rewriteTaskRecord(tasks);
        return ui.showMarkedDone(task);
    }
}
