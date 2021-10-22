package exception;

/**
 * Template for an exception: EmptyDescroption and RandomDescription.
 * Allows users to output error message.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * constructor for creating an EmptyDescription Exception.
     */
    public DukeException(String message) {
        this.message = message;
    }

    public String get_message() {
        return this.message;
    }

    /**
     * Returns the error message.
     *
     * @return String error message.
     */
    public String output_error() {
        return "";
    }

}
