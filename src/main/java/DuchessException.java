/**
 * This class implements a DuchessException to be thrown by Duchess
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class DuchessException extends Exception {

    /**
     * Constructor for DuchessException
     * @param errorMessage the error message associated with the exception
     */
    public DuchessException(String errorMessage) {
        super(errorMessage);
    }
}
