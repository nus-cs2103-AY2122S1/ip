package pika.exception;

/**
 * Used to handle any errors in Duke.
 */
public class PikaException extends Exception {

    /**
     * Constructor for the Error.
     */
    protected PikaException() {
    }

    /**
     * Constructor for the Error with the return message.
     *
     * @param message Message to be printed out to the users.
     */
    public PikaException(String message) {
        super(message);
    }
}
