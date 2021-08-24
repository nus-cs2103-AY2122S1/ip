package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class CommandDelete extends Command {
    int index;

    public CommandDelete(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String msg = tasks.deleteTask(index);
            ui.printMsg(msg);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.isExit = true;
        }
    }
}
