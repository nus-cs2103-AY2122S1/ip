package command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    protected FindCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        try {
            return tasklist.findTasks(this.args);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
