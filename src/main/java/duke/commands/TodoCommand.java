package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.Todo;

public class TodoCommand extends AddCommand {
    public TodoCommand(String rest) throws DukeException {
        super(new Todo(rest));
    }
}
