/**
 * DukeException is a runtime exception for exceptions generated due to invalid inputs or problems adding tasks.
 */
public class DukeException extends RuntimeException{
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ":'( OOPS!!! " + super.getMessage();
    }
}
