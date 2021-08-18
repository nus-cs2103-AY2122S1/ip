public class NoArgumentDukeException extends DukeException {
    public NoArgumentDukeException() {
        super(String.format("Arguments required for this command."));
    }
}
