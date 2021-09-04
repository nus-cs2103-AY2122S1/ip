package duke.exception;

/**
 * This class encapsulates exception due to missing accompanying date/time in user input.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class MissingDateTimeException extends DukeException {
    /**
     * Constructs a MissingDateTimeException.
     *
     * @param format Format of command that should be used to specify a date/time.
     */
    public MissingDateTimeException(String format) {
        super(String.format("MissingDateTimeException: Command issued requires you to specify a date/time using "
                + "%s", format));
    }
}
