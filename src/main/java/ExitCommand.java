package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.write(tasks);
        ui.printExit();
    }

    public boolean isExit() {
        return true;
    }

}