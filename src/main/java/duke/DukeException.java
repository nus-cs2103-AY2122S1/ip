package duke;


/**
 * DukeException encapsulates the checked exceptions for the program.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}