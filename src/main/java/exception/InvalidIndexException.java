package exception;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int start, int end, int actual) {
        super("You entered an invalid task number of " + actual + ". Please enter a task number from " + start + " to " + end + ".");
    }
}
