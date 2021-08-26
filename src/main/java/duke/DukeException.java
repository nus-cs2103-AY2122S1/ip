package duke;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /** Concats this exception message with another exception message */
    public DukeException concat(Exception e) {
        return new DukeException(this.getMessage() + " " + e.getMessage());
    }

    @Override
    public String toString() {
        return "∑(O_O;) Oh no!! " + getMessage();
    }
}
