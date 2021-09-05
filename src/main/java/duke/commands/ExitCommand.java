package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.TaskList;

/**
 * Command that exits the chat bot.
 */
public class ExitCommand extends Command {

    public ExitCommand() {}

    /**
     * Executes the command.
     *
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayBye();
    }

}
