package duke.command;

import duke.exception.DukeException;
import duke.exception.NoSuchCommandException;

/**
 * This class encapsulates the command dealing with any invalid/unrecognised commands issued.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class UnrecognisedCommand extends Command {
    private String input;

    public UnrecognisedCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute() throws DukeException {
        throw new NoSuchCommandException(this.input.split(" ", 2)[0]);
    }
}
