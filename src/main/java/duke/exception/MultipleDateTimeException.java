package duke.exception;

/**
 * This class encapsulates exception due to multiple date/time input by user.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class MultipleDateTimeException extends DukeException {
    public MultipleDateTimeException() {
        super("MultipleDateTimeException: Multiple date/time detected. Please input only one date/time!");
    }
}
