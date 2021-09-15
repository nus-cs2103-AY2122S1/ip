package duke.exception;

/**
 * Abstract representation of all duke exceptions.
 */
public abstract class DukeException extends Exception {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format(":(( Oops!");
    }
}
