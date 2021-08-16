public class UnknownCommandException extends DukeException {
	public UnknownCommandException(String command) {
		super(String.format("The command, \"%s\", is unknown.", command));
	}
}