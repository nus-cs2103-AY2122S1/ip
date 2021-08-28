package utils.command;

import utils.storage.Storage;
import utils.task.TaskList;
import utils.ui.Ui;

public class ListCommand extends Command {
    private static final String MESSAGE_LIST = "Here is the list of your tasks: ";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = MESSAGE_LIST + "\n" + tasks.toString();
        ui.print(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
