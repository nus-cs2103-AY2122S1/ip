package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    /**
     * Executes the specified command.
     *  @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return ui.showEmptyList();
        } else {
            String output = "";
            output += "Here are the tasks in your list:\n\n";
            for (int j = 0; j < tasks.size(); j++) {
                output += (j + 1) + ". " + tasks.get(j).toString() + "\n";
            }
            return output;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
