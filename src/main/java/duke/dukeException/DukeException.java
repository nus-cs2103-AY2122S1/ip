package duke.dukeException;

/**
 * This program called Duke Exception is for handling Exceptions in Duke.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
