/**
 * An Exception class for Duke specific Exceptions.
 *
 * @author Lethicia
 */
public class DukeException extends Exception{
    /**
     * Constructor for a Duke Exception
     *
     * @param errorMessage description of the Duke Exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
