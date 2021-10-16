package duke.exception;

/**
 * This is the DukeException class to handle Duke-specific exceptions.
 */
public class DukeException extends Exception {
	public DukeException(String errorMessage) {
		super(errorMessage);
	}
}
