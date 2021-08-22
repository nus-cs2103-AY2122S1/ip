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
        // Removes a Task from the TaskList instance
        Task removedTask = tasks.deleteTask(taskId);

        // Displays a message indicating the task has been successfully deleted from the list
        String message = "Got it! I've removed this task:\n" + "  " + removedTask + "\n";
        if (tasks.getNumberOfTasks() <= 1) {
            message += "Now you have " + tasks.getNumberOfTasks() + " task in the list.";
        } else {
            message += "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        }
        ui.showCommandDone(message);

        // Saves the current task list to the hard drive
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
