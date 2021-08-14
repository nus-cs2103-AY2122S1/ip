/**
 * This class encapsulates all exceptions specific to Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class DukeException extends Exception {
    /**
     * Instantiates a new Duke exception.
     *
     * @param error the error
     */
    public DukeException(String error) {
        super(error);
    }
}
