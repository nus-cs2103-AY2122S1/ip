package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.UserInterface;

public class CommandDone extends Command {
    private String taskName;
    public CommandDone(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        Task task = taskList.find(taskName);
        if (task == null){
            ui.displayError("No such file found: " + taskName);
        } else {
            task.markDone();
            ui.print("Yay :) This task is done:\n" + task.toString());
        }
    }
}
