package duke.utils;

public class DukeException extends Exception {
    public DukeException(String e) {
        super("OOPS!!! " + e);
    }
}
