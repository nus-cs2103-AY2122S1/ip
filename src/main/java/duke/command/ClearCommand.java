package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ClearCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showConfirmClearTasks();
        String userConfirmation = ui.readCommand();
        if (userConfirmation.equalsIgnoreCase("y")
                || userConfirmation.equalsIgnoreCase("yes")) {
            tasks.clearTaskList();
            ui.showClearTasks();
            storage.clearTasks();
        } else {
            ui.showTasksNotCleared();
        }
    }
}
