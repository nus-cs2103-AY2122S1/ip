package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.Event;

public class EventCommand extends AddCommand {
    public EventCommand(String rest) throws DukeException {
        super(new Event(rest.split(" /[a-z][a-z] ")));
    }
}
