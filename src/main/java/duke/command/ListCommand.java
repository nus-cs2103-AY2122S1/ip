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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Show list
        String lst_display = "\n";
        for (int i = 0; i < taskList.size(); i++) {
            lst_display = lst_display + String.format("\t%d. %s\n", i + 1, taskList.getTask(i));
        }
        ui.reply(lst_display);
    }
}
