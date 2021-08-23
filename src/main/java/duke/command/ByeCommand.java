package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {}

    @Override
    public boolean isExit() {
        return true;
    }
}
