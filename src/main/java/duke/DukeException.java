package duke;

/**
 * To handle Duke Exception.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
