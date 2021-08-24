package duke.command;

import duke.*;
import duke.task.TaskList;

public class InvalidCommand extends Command {

    public InvalidCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.invalidUserInput();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
