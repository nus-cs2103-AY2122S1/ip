package exception;

/**
 * Handle exception of empty task descriptions.
 * Allows users to output error message.
 */
public class EmptyDescription extends DukeException {

    /**
     * constructor for creating an EmptyDescription Exception.
     */
    public EmptyDescription(String message) {
        super(message);
    }

    /**
     * Returns the error message.
     *
     * @return String error message.
     */
    @Override
    public String output_error() {
        return "OOPS! The description of " + this.get_message() + " cannot be empty.";
    }

}
