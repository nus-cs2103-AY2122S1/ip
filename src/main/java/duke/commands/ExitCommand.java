package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /**
     * The command word that identifies an ExitCommand instance.
     */
    public static final String COMMAND_WORD = "bye";

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + " - exit this program";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printExitMessage();
    }
}
