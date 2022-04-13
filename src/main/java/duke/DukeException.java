package duke;

/**
 * DukeException is a RuntimeException that occurs when the user inputs invalid commands.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
