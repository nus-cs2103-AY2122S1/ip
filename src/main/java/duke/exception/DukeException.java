package duke.exception;

public class DukeException extends Exception {
    public DukeException(String str) {
        super("Error: Whoops... " + str);
    }
}
