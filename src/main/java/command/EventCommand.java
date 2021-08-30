package command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class EventCommand extends Command {

    protected EventCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        tasklist.add(Task.createTask("event", this.args));
    }
}
