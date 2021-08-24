package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class CommandDone extends Command {
    int index;

    public CommandDone(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String msg = tasks.markTaskDone(index);
            ui.printMsg(msg);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
