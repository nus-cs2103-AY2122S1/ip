/**
 * DukeException class represents exceptions specific to Duke and provides
 * understandable error message.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns string representing error message.
     *
     * @return string representation of error message.
     */
    @Override
    public String toString() {
        return super.getMessage() + "\n\t";
    }
}
