package duke.exceptions;

/**
 * Exception class to handle the invalid commands.
 */
public class UnclearInstructionException extends Exception {
    public UnclearInstructionException(String message) {
        super(message);
    }
}
