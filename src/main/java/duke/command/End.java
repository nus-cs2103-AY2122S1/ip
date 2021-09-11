package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.UiInterface;

/**
 * Class that handles the Bye command
 */
public class End extends Command {

    private static final String END_MESSAGE = "\n\tSad to see you go :(\n\t...shutting down...";

    /**
     * Executes the End command.
     *
     * @param taskList Current list of tasks
     * @param ui Ui to interact with user
     * @param storage Storage that allows loading/saving
     * @throws DukeException if an error is encountered
     */
    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) {
        ui.showBye(END_MESSAGE);
    }

    /**
     * Returns if the command is an exit.
     *
     * @return boolean indicating if command is exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
