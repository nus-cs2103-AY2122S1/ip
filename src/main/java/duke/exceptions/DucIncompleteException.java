package duke.exceptions;

public class DucIncompleteException extends DucException {
    public DucIncompleteException() {
        super("☹ Oops, I'm sorry but this is incomplete!\n");
    }
}
