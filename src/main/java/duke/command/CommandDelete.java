package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

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
            tasks.saveToFile(storage);
            ui.printMsg(msg);
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
