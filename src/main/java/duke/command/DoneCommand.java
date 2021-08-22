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
        // Marks the task as done
        Task completedTask = tasks.markAsDone(this.taskId);

        // Displays a message indicating the task has been successfully marked as done
        ui.showCommandDone("Nice! I've marked this task as done:\n  " + completedTask);

        // Saves the current task list to the hard drive
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
