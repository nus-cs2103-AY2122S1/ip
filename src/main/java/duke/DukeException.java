package duke;

public class DukeException extends Exception {
    private final String error;

    public DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }
}
