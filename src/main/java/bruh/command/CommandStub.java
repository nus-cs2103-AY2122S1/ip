package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

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
