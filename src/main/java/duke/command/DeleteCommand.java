package duke.command;

import duke.TaskManager;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command "delete x" to delete a task at index x (1-indexed).
 */
public class DeleteCommand extends Command {
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        Task deletedTask = taskManager.deleteTask(id);
        ui.reply(String.format("Noted. I've removed this task: \n" +
                "%s\n" +
                "Now you have %d tasks in the list.", deletedTask.toString(), taskManager.taskCount())
        );
    }
}
