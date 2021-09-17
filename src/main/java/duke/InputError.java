package duke;

/**
 * Custom Error when users enters an invalid input.
 * @author Nikki
 */
public class InputError extends Exception {

    /**
     * Creates an InputError.
     *
     * @param errorMessage Error message of invalid input.
     */
    public InputError(String errorMessage) {
        super(errorMessage);
    }
}
