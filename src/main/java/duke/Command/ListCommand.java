package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("All tasks:");
        ui.print(tasks.allTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
