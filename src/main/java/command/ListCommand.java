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
    public void execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        tasklist.printTasks();
    }
}
