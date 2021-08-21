package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;

public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String rest) throws DukeException {
        super(new Deadline(rest.split(" /[a-z][a-z] ")));
    }
}
