import java.util.*;

abstract class DukeException extends IllegalArgumentException {
	public DukeException(String message) {
		super("Duke has encountered an problem: " + message);
	}

	/* Future messages may require more lines to passed to outputhandler */
	public List<String> getHelpMessages() {
		return new ArrayList<>();
	}
}