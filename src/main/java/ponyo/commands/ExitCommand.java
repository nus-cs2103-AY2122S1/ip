package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.storage.Storage;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        return null;
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
