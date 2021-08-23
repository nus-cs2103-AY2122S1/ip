package duke.command;

import duke.TaskManager;
import duke.Ui;

/**
 * Represents the command "list" to list the tasks in given taskManager in numbered list format.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.reply(
                String.format("Here are the tasks in your list:\n%s", taskManager.listTasks())
        );
    }
}
