package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            storage.writeToDisk(taskList.compileTasks());
        } catch (DukeException e) {
            e.printStackTrace();
            ui.respond(e.getMessage());
        }
    }
}
