package duke.command;

import duke.DukeList;
import duke.exception.DukeException;

public class InvalidCommand extends DukeCommand {
    private final DukeException e;

    /**
     * Public constructor to create a InvalidCommand.
     *
     * @param e Exception thrown.
     */
    public InvalidCommand(DukeException e) {
        super();
        this.e = e;
    }

    @Override
    public String run(DukeList list) throws DukeException {
        throw e;
    }
}
