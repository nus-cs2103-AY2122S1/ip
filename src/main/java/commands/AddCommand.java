package commands;

import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class AddCommand extends Command{
    private static final String NEW_TASK_MSG = "New task added:";

    Task task;
    
    public AddCommand(Task task) {
        this.task = task;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        String message = NEW_TASK_MSG + "\n" + this.task.toString() + "\n" + tasks.getTaskCountString();
        ui.printResponse(message);

        return true;
    }
}
