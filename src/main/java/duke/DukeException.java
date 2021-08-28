package duke;

/**
 * An Exception subclass that is specific to the misbehaviours of Duke programme.
 *
 * @author Gu Geng
 */
public class DukeException extends Exception {

    /**
     * Returns an DukeException instance.
     * The Exception is initialised with an error message in String.
     *
     * @param errorMessage Description of the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
