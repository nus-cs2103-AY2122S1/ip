package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ListCommand extends Command {

    public ListCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("Currently no tasks!");
        }
        ui.showTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
