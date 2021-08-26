package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command{
    private final Task toAdd;

    public AddCommand(Task toAdd){
        this.toAdd = toAdd;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.toAdd);
        ui.printAdd(this.toAdd, tasks.getSize());
    }
}
