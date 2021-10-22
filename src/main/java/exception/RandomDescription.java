package exception;

/**
 * Handle exception of random unrelated descriptions.
 * Allows users to output error message.
 */
public class RandomDescription extends DukeException {

    /**
     * constructor for creating an EmptyDescription Exception.
     */
    public RandomDescription(String message) {
        super(message);
    }

    /**
     * Returns the error message.
     *
     * @return String error message.
     */
    @Override
    public String output_error() {
        return "OOPS!! I'm sorry, but I don't know what that means ;-(";
    }
}
