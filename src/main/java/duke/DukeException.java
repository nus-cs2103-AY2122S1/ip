package duke;

public class DukeException extends Exception {
    DukeException() {
        super("todo/event/delete cannot be empty.");
    }

    DukeException(String message, String description) {
        super("The description of a " + message + " cannot be empty. It has to be in this format: " + description);
    }

    DukeException(String message) {
        super(message);
    }
}
