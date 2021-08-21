package duke.command;

import duke.exception.TaskAlreadyDoneException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexOutOfBoundsException,
            TaskAlreadyDoneException {
        Task completedTask = tasks.markAsDone(this.taskId);
        ui.showCommandDone("Nice! I've marked this task as done:\n  " + completedTask);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
