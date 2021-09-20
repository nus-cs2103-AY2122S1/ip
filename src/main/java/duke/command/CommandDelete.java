package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.UserInterface;

public class CommandDelete extends Command{
    public static final String DELETE_NAME = "delete";
    private String taskName;

    public CommandDelete(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        Task deletedTask = taskList.delete(taskName);
        if (deletedTask != null) {
            ui.print("Noted. This task has been deleted");
            ui.print(deletedTask.toString());
        } else {
            ui.print("Oops! That task was not found.");
        }
    }
}
