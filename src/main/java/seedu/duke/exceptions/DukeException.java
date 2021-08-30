package seedu.duke.exceptions;

public class DukeException extends RuntimeException {

    private final String err;

    public DukeException(String err) {
        this.err = err;
    }

    @Override
    public String toString() {
        return this.err;
    }

}
