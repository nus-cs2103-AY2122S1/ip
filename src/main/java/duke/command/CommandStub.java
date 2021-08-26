package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * A stub class which simulates a command for testing.
 */
public class CommandStub extends Command {

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return "Test command run";
    }

    @Override
    public String getDescription() {
        return "Test command description";
    }
}
