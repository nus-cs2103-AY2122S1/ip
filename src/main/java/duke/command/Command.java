package duke.command;

import duke.exception.DukeException;

/**
 * This class acts as a base class for all commands of Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute() throws DukeException;

    /**
     * Convert input command.
     *
     * @param input Command issued by user in String format.
     * @return Corresponding command.
     */
}
