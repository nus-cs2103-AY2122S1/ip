package duke.exceptions;

public class DukeException extends RuntimeException {

    private String err;

    public DukeException(String err) {
        this.err = err;
    }

    @Override
    public String toString() {
        return this.err;
    }

}
