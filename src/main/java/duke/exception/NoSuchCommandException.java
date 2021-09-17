package duke.exception;

/**
 * This class encapsulates exception due to unrecognized user inputs.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class NoSuchCommandException extends DukeException {
    /**
     * Constructs a NoSuchCommandException specified with the command that caused the error.
     *
     * @param command Command that caused a NoSuchCommandException to be thrown
     */
    public NoSuchCommandException(String command) {
        super(String.format("NoSuchCommandException: Unrecognised command `%s`. Perhaps you made a typo?",
                command));
    }
}
