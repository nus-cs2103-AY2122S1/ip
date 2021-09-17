package duke.commands;

import java.io.IOException;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * when exiting the program
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveTaskListToDisk(tasks);
        ui.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
