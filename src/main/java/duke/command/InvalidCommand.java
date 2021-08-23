package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeInvalidCommandException();
    }
}
