package duke;

import javafx.application.Platform;

/**
 * Command to exit from Duke.
 *
 * @author felix-ong
 */
public class ExitCommand extends Command {

    /**
     * Executes the specific actions for this command.
     *
     * @param tasks Handles the list of tasks.
     * @param ui Handles the user interface.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
    }

    /**
     * Returns true
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Bye! Feel free to ask me for help again anytime!";
    }
}
