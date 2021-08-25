package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String USAGE_MESSAGE = "To close Duke, use 'bye'";

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        ui.farewell();
        storage.saveTasks(taskManager.toText());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
