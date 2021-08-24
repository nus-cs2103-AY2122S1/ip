package duke;

/**
 * Exception when users type invalid input.
 */
public class DukeExcpetion extends Exception {
    public DukeExcpetion(String message) {
        super(message);
    }

    public String toString() {
        return "\uD83E\uDD74 OOPS!!! " + this.getMessage();
    }
}