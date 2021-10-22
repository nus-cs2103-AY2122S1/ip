package captain.command;

import captain.DukeException;
import captain.DukeException.InvalidInputException;
import captain.Storage;
import captain.Ui;
import captain.task.TaskList;

public class InvalidCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new InvalidInputException();
    }
}
