package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;

/**
 * Encapsulates the Deadline command's operations
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Constructor for DeadlineCommand
     *
     * @param rest the user input after command
     * @throws DukeException if user input is empty or invalid
     */
    public DeadlineCommand(String rest) throws DukeException {
        super(new Deadline(rest.split(" /[a-z][a-z] ")));
    }
}
