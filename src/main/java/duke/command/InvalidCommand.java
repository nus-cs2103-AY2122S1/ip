package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidEntryException;
import duke.storage.Storage;
import duke.ui.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            throw new InvalidEntryException("error");
        } catch (DukeException e) {
            return e.getError();
        }
    }
}
