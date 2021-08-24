package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public class EmptyCommand extends Command {
    public void execute(TaskList taskList, Storage storage) {

    }

    @Override
    public boolean getIsExit() {
        return false;
    }
}
