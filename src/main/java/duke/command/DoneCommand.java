package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command{
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getIndex(index).setDone();
        ui.showMessage(message(tasks));
    }

    @Override
    public String message(TaskList tasks) {
        return "Nice! I've marked this task as done: \n"
                + tasks.getIndex(index).toString() + "\n";
    }
}
