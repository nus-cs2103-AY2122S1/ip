package duke.exception;

public class DataFileChangedException extends DukeException {
    public DataFileChangedException() {
        super("Contents of file have been changed!");
    }
}
