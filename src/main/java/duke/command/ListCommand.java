package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * Represents a command to list all tasks
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String USAGE_TEXT = "Usage: list";

    /**
     * Displays all tasks in given TaskList to the user.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        // Display taskList
        return ui.reply(taskList.toString());
    }
}
