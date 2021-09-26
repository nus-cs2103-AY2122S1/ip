package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.TaskList;

/**
 * Command that displays list of tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    /**
     * Executes the command.
     *
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        return ui.displayList(taskList);
    }

}
