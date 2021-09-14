package duke.exceptions;

public class DukeIncompleteException extends DukeException {
    public DukeIncompleteException() {
        super("☹ Oops, I'm sorry but this is incomplete!\n");
    }
}
