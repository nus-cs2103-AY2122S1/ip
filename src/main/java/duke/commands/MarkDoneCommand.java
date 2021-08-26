package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class MarkDoneCommand extends Command {
    private int taskNo;
    
    public MarkDoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(this.taskNo - 1);
        if (task.getCompletionStatus()) {
            ui.showTaskDone(task);
        } else {
            tasks.taskDone(this.taskNo);
            storage.rewriteTaskRecord(tasks);
            ui.showMarkedDone(task);
        }
    }
}
