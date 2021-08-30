package command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    protected FindCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        tasklist.findTasks(this.args);
    }
}
