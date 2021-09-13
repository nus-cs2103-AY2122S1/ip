package duke.exception;

public class DukeSortException extends DukeException {
    public DukeSortException() {
        super("Error: Invalid use of 'sort' command! Enter 'sort reverse' if you want to reverse order of list.");
    }
}
