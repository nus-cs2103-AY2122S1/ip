package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(this.message(tasks));
        tasks.removeIndex(index);
    }

    @Override
    public String message(TaskList tasks) {
        return "Noted. I've removed this task:\n"
                + tasks.getIndex(index).toString() + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list.\n";
    }
}
