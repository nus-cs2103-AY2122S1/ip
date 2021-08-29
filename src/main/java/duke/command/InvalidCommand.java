package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class InvalidCommand extends Command {

    public InvalidCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry I do not understand this directive.");
    }

    public boolean isExit() {
        return false;
    }
}
