package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class NonsenseCommand extends Command {
    // empty as the string input is invalid
    public NonsenseCommand() {
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
