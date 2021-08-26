package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.printTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
