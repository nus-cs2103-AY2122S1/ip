package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.UI;

/**
 * A command which exits from Duke.
 */
public class ExitCommand extends Command {
    /**
     * Instructs Duke to exit.
     *
     * @param task the task to be executed in the command.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     */
    @Override
    public void execute(Tasklist task, UI ui, FileManager fileManager) {
        ui.sayBye();
    }

    /**
     * Returns if the function is a exit command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
