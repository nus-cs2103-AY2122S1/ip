package duke.command;

import duke.DukeException;
import duke.DukeException.InvalidInputException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new InvalidInputException();
    }
}
