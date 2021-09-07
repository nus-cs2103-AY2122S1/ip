package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 *  The ByeCommand class is a Command that displays a goodbye message and exits the chatbot
 */
public class ByeCommand extends Command {

    /**
     * Calls a ui method to display a goodbye message in the ui.
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which the result message of the command execution is displayed to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.displayBye();
    }
}