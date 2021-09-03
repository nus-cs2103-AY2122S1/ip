package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UiInterface;

/**
 * Class that handles the Bye command
 */
public class End extends Command {

    private static final String END_MESSAGE = "\n\tSad to see you go :(\n\t...shutting down...";

    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) {
        ui.showBye(END_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
