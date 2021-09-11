package jackie;

/**
 * An Exception subclass that is specific to the misbehaviours of Duke programme.
 *
 * @author Gu Geng
 */
public class JackieException extends Exception {

    /**
     * Returns an DukeException instance.
     * The Exception is initialised with an error message in String.
     *
     * @param errorMessage Description of the error.
     */
    public JackieException(String errorMessage) {
        super(errorMessage);
    }
}
