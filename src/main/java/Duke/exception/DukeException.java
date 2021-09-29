package Duke.exception;

import Duke.Duke;

public class DukeException extends Exception {
    public String error;

    public DukeException(String error) {
        super(error);
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }
}
