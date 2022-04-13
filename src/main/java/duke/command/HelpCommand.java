package duke.command;

import duke.util.Message;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for help.
 *
 * @author marcuspeh
 * @version BCD-Extension
 * @since 6 Sep 2021
 */
public class HelpCommand implements Command {
    /**
     * Returns a message containing all the commands that can be used.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui       To interact with the user.
     * @return message to be used by either the graphic UI or command line UI.
     */
    @Override
    public Message execute(TaskList taskList, Ui ui) {
        return ui.formatHelpMessage();
    }
}
