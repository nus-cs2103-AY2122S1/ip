package duke;

/**
 * DukeException is a RuntimeException that occurs when the user inputs invalid commands.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}