package command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class TodoCommand extends Command {

    protected TodoCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        return tasklist.add(Task.createTask("todo", this.args));
    }
}
