package duke.command;

import duke.*;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage(this.message(tasks));
    }

    @Override
    public String message(TaskList tasks) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n";
    }
}
