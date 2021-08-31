package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.storage.Storage;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    private static final String MESSAGE_BYE = "\tBye. Hope to see you again soon!";

    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        return null;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
