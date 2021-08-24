package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

public class ExitCommand extends Command {
    private static final String MESSAGE_BYE = "\tBye. Hope to see you again soon!";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(MESSAGE_BYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
