package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the bye command.
 */
public class ByeCommand implements Command {

    /**
     * Displays the goodbye message.
     *
     * @param tasks User's list of tasks.
     * @param ui Duke's UI.
     * @return The String representation of Duke's response.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.getGoodbyeMsg();
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return true;
    }
}
