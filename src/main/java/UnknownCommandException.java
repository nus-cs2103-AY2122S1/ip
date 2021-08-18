public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String input) {
        super("Command \"" + input + "\" not found.");
    }
}
