package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.Utils;
import duke.task.Task;

public class FindCommand extends Command {

    public FindCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        TaskList searchedTasks = tasks.searchTasks(userArgument);
        if (searchedTasks.isEmpty()) {
            ui.showMessage("No matching tasks.");
            return;
        }
        ui.showMessage("Here are the matching tasks.");
        ui.showTasks(searchedTasks);
    }

    public boolean isExit() {
        return false;
    }
}
