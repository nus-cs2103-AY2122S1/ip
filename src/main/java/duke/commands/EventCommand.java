package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.Event;

/**
 * Encapsulates the Event command's operations
 */
public class EventCommand extends AddCommand {
    /**
     * Constructor for EventCommand
     *
     * @param rest the user input after the command
     * @throws DukeException if user input empty or invalid
     */
    public EventCommand(String rest) throws DukeException {
        super(new Event(rest.split(" /[a-z][a-z] ")));
    }
}
