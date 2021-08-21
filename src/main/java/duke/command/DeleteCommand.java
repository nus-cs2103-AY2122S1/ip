package duke.command;

import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexOutOfBoundsException {
        Task removedTask = tasks.deleteTask(taskId);

        String message = "Got it! I've removed this task:\n" + "  " + removedTask + "\n";
        if (tasks.getNumberOfTasks() <= 1) {
            message += "Now you have " + tasks.getNumberOfTasks() + " task in the list.";
        } else {
            message += "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        }
        ui.showCommandDone(message);

        storage.save(tasks);
    }
}
