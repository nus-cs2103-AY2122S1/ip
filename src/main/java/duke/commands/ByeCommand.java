package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 *  The ByeCommand class is a Command that displays a goodbye message and exits the chatbot
 */

public class ByeCommand extends Command {

    /**
     * Default constructor for ByeCommand
     */
    public ByeCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Given a list of tasks and ui, the program has a function call to display a goodbye message in the ui.
     *
     * @param taskList list of tasks
     * @param ui ui in which the bye message is displayed
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayBye();
    }
}
