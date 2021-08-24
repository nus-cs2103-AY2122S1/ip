package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class CommandAdd extends Command {
    Task taskToAdd;

    public CommandAdd(Task taskToAdd) {
        super();
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = tasks.addTask(this.taskToAdd);
        ui.printMsg(msg);
    }
}
