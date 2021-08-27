package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class abstracts the exit command that the user wants to execute.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Execute the command to exit Duke.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param ui      The UI handler of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodBye();
    }

    /**
     * Returns whether the Command is an ExitCommand.
     *
     * @return True if and only if the command is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
