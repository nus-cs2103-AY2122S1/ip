package exception;

/**
 * Template for an exception: EmptyDescroption and RandomDescription
 * Allows users to output error message
 */
public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String get_message() {
        return this.message;
    }

    public String output_error() {
        return "";
    }

}
