package duke.exception;

public class DukeNoSuchCommandException extends DukeException {
    public DukeNoSuchCommandException() {
        super("I don't quite understand you. :-(");
    }
}