package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printBye();
    }

    @Override
    public boolean getIsExit() {
        return true;
    };
}
