package command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    protected ListCommand() {
        super("");
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        return tasklist.printTasks();
    }
}
