package duke;

public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(Duke.styleResponse("Oops, " + message));
    }
}
