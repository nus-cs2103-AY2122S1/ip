package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Lists the current tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printMsg("You do not have any tasks right now.");
        } else {
            String[] temp = new String[tasks.size() + 1];
            temp[0] = "Here are the tasks in your list:";

            for (int i = 1; i < tasks.size() + 1; ++i) {
                temp[i] = i + "." + tasks.get(i - 1).toString();
            }

            ui.printMsg(temp);
        }
    }
}
