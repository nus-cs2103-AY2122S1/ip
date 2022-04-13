package duke.exception;

public class NoSuchTaskException extends Exception {

    /**
     * Constructor for the Exception.
     *
     * @param message The error message.
     */
    public NoSuchTaskException(String message) {
        super(message);
    }

}
