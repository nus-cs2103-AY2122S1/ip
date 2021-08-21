/**
 * DukeException is a runtime exception for exceptions generated due to invalid inputs or problems adding tasks.
 */
public class DukeException extends RuntimeException{

    /**
     * Creates a new runtime exception when there is a problem with inputs or adding tasks.
     *
     * @param message The description of the error
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ":'( OOPS!!! " + super.getMessage();
    }
}
