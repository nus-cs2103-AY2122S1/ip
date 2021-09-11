package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * ExitCommand will close Duke program when executed.
 */
public class ExitCommand extends Command {

    private final boolean EXIT = true;

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    public boolean isExit() {
        return EXIT;
    }
}
