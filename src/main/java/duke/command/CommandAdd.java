package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class CommandAdd extends Command {
    Task taskToAdd;

    public CommandAdd(Task taskToAdd) {
        super();
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String msg = tasks.addTask(this.taskToAdd);
            tasks.saveToFile(storage);
            ui.printMsg(msg);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
