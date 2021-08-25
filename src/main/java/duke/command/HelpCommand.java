package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class HelpCommand extends Command {
    private static final String[] ALL_USAGES = new String[]{
            AddToDoCommand.USAGE_MESSAGE,
            AddDeadlineCommand.USAGE_MESSAGE,
            AddEventCommand.USAGE_MESSAGE,
            ListCommand.USAGE_MESSAGE,
            CompleteTaskCommand.USAGE_MESSAGE,
            DeleteTaskCommand.USAGE_MESSAGE,
            ExitCommand.USAGE_MESSAGE
    };

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        ui.print(String.join("\n", ALL_USAGES));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
